package com.wangduwei.java_basic.multythread.deadlock;


import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;

/**
 * 来自外星方法的危害
 * <p>
 * 请看update和update2方法
 * <p>
 * 1、updateProgress本身持有锁，回调外部不确定性的逻辑，外部可能也有锁，存在死锁的可能性
 * 2、通过updateProgress2保护性复制，方法本身不需要加锁，排除了死锁的可能性
 * 3、直接使用{@link java.util.concurrent.CopyOnWriteArrayList}
 */
class Downloader extends Thread {

    private InputStream in;
    private OutputStream out;
    private ArrayList<ProgressListener> listeners;


    public Downloader(URL url, String outputFilename) throws IOException {
        in = url.openConnection().getInputStream();
        out = new FileOutputStream(outputFilename);
        listeners = new ArrayList<>();
    }

    public synchronized void addListener(ProgressListener listener) {
        listeners.add(listener);
    }

    public synchronized void removeListener(ProgressListener listener) {
        listeners.remove(listener);
    }

    /**
     * 避免持有锁时调用外部方法，外部的逻辑也有可能持有锁，可能造成死锁！！
     *
     * @param n
     */
    private synchronized void updateProgress(int n) {
        for (ProgressListener listener : listeners)
            listener.onProgress(n);
    }

    /**
     * 解决方法
     *
     * @param n
     */
    private void updateProgress2(int n) {
        ArrayList<ProgressListener> listenersCopy;
        synchronized (this) {//保护性复制
            listenersCopy = (ArrayList<ProgressListener>) listeners.clone();
        }
        for (ProgressListener listener : listenersCopy)
            listener.onProgress(n);
    }

    public void run() {
        int n = 0, total = 0;
        byte[] buffer = new byte[1024];
        try {
            while ((n = in.read(buffer)) != -1) {
                out.write(buffer, 0, n);
                total += n;
                updateProgress(total);
            }
            out.flush();
        } catch (IOException e) {
        }
    }


    interface ProgressListener {
        public void onProgress(int n);
    }

}
