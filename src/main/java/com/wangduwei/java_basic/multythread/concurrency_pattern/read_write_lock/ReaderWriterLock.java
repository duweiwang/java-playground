package com.wangduwei.java_basic.multythread.concurrency_pattern.read_write_lock;


import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;

/**
 * 此类控制读写锁的访问
 *
 * <p>Allows multiple readers to hold the lock at same time, but if any writer holds the lock then
 * readers wait. If reader holds the lock then writer waits. This lock is not fair.
 */
public class ReaderWriterLock implements ReadWriteLock {

    private final Object readerMutex = new Object();

    private int currentReaderCount;

    /**
     * Global mutex is used to indicate that whether reader or writer gets the lock in the moment.
     *
     * <p>1. When it contains the reference of {@link #readerLock}, it means that the lock is
     * acquired by the reader, another reader can also do the read operation concurrently. <br> 2.
     * When it contains the reference of reference of {@link #writerLock}, it means that the lock is
     * acquired by the writer exclusively, no more reader or writer can get the lock.
     *
     * <p>This is the most important field in this class to control the access for reader/writer.
     */
    private final Set<Object> globalMutex = new HashSet<>();

    private final ReadLock readerLock = new ReadLock();
    private final WriteLock writerLock = new WriteLock();

    @Override
    public Lock readLock() {
        return readerLock;
    }

    @Override
    public Lock writeLock() {
        return writerLock;
    }

    /**
     * return true when globalMutex hold the reference of writerLock.
     */
    private boolean doesWriterOwnThisLock() {
        return globalMutex.contains(writerLock);
    }

    /**
     * Nobody get the lock when globalMutex contains nothing.
     */
    private boolean isLockFree() {
        return globalMutex.isEmpty();
    }

    private class ReadLock implements Lock {

        @Override
        public void lock() {
            synchronized (readerMutex) {
                currentReaderCount++;
                if (currentReaderCount == 1) {
                    acquireForReaders();
                }
            }
        }

        /**
         * Acquire the globalMutex lock on behalf of current and future concurrent readers. Make sure no
         * writers currently owns the lock.
         */
        private void acquireForReaders() {
            // Try to get the globalMutex lock for the first reader
            synchronized (globalMutex) {
                // If the no one get the lock or the lock is locked by reader, just set the reference
                // to the globalMutex to indicate that the lock is locked by Reader.
                while (doesWriterOwnThisLock()) {
                    try {
                        globalMutex.wait();
                    } catch (InterruptedException e) {
                        String message = "InterruptedException while waiting for globalMutex in acquireForReaders";
                        System.out.println(message + e);
                        Thread.currentThread().interrupt();
                    }
                }
                globalMutex.add(this);
            }
        }

        @Override
        public void unlock() {
            synchronized (readerMutex) {
                currentReaderCount--;
                // Release the lock only when it is the last reader, it is ensure that the lock is released
                // when all reader is completely.
                if (currentReaderCount == 0) {
                    synchronized (globalMutex) {
                        // Notify the waiter, mostly the writer
                        globalMutex.remove(this);
                        globalMutex.notifyAll();
                    }
                }
            }

        }

        @Override
        public void lockInterruptibly() {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean tryLock() {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean tryLock(long time, TimeUnit unit) {
            throw new UnsupportedOperationException();
        }

        @Override
        public Condition newCondition() {
            throw new UnsupportedOperationException();
        }

    }

    private class WriteLock implements Lock {

        @Override
        public void lock() {
            synchronized (globalMutex) {

                // Wait until the lock is free.
                while (!isLockFree()) {
                    try {
                        globalMutex.wait();
                    } catch (InterruptedException e) {
                        System.out.println("InterruptedException while waiting for globalMutex to begin writing" + e);
                        Thread.currentThread().interrupt();
                    }
                }
                // When the lock is free, acquire it by placing an entry in globalMutex
                globalMutex.add(this);
            }
        }

        @Override
        public void unlock() {
            synchronized (globalMutex) {
                globalMutex.remove(this);
                // Notify the waiter, other writer or reader
                globalMutex.notifyAll();
            }
        }

        @Override
        public void lockInterruptibly() {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean tryLock() {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean tryLock(long time, TimeUnit unit) {
            throw new UnsupportedOperationException();
        }

        @Override
        public Condition newCondition() {
            throw new UnsupportedOperationException();
        }
    }

}
