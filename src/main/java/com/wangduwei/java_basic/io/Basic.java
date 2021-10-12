package com.wangduwei.java_basic.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.FileChannel;

/**
 * @author : wangduwei
 * @date : 2020/5/17
 * @description :
 */
public class Basic {
    public static void copyFileByStream(File source, File des) throws IOException {
        try (InputStream is = new FileInputStream(source);
             OutputStream os = new FileOutputStream(des);) {
            byte[] bufer = new byte[1024];
            int length;
            while ((length = is.read(bufer)) > 0) {
                os.write(bufer, 0, length);
            }
        }
    }

    public static void copyFileByChannel(File source, File des) throws
            IOException {
        try (FileChannel sourceChannel = new FileInputStream(source).getChannel();
             FileChannel targetChannel = new FileOutputStream(des).getChannel()) {
            for (long count = sourceChannel.size(); count > 0; ) {
                long transferred = sourceChannel.transferTo(sourceChannel.position(), count, targetChannel);
                sourceChannel.position(sourceChannel.position() + transferred);
                count -= transferred;
            }
        }
    }
}
