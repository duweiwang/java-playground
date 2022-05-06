/*
 * The MIT License
 * Copyright © 2014-2021 Ilkka Seppälä
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.wangduwei.java_basic.multythread.concurrency_pattern.read_write_lock;

import java.util.concurrent.locks.Lock;

public class Reader implements Runnable {

    private final Lock readLock;

    private final String name;

    private final long readingTime;

    public Reader(String name, Lock readLock, long readingTime) {
        this.name = name;
        this.readLock = readLock;
        this.readingTime = readingTime;
    }

    public Reader(String name, Lock readLock) {
        this(name, readLock, 250L);
    }

    @Override
    public void run() {
        readLock.lock();
        try {
            read();
        } catch (InterruptedException e) {
            System.out.println("InterruptedException when reading" + e);
            Thread.currentThread().interrupt();
        } finally {
            readLock.unlock();
        }
    }

    public void read() throws InterruptedException {
        System.out.println("{} begin" + name);
        Thread.sleep(readingTime);
        System.out.println("{} finish after reading {}ms" + name + readingTime);
    }
}
