/*
 * The MIT License
 * Copyright © 2014-2019 Ilkka Seppälä
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

package com.wangduwei.java_basic.multythread.async_method_invocation;


import java.util.concurrent.Callable;

/**
 * 异步方法调用模式。
 * 关键点：
 * 1、AsyncResult:异步执行结果的中间状态容器
 * 2、AsyncCallback:任务执行完成后执行的逻辑
 * 3、AsyncExecutor:管理异步任务的执行
 *
 * <p>main方法演示了异步调用流程. The main thread starts multiple
 * tasks with variable durations and then continues its own work. When the main thread has done it's
 * job it collects the results of the async tasks. Two of the tasks are handled with callbacks,
 * meaning the callbacks are executed immediately when the tasks complete.
 *
 * <p>异步结果和回调之间的区别主要是，异步结果发生在主线程，而回调发生在工作线程。在使用线程池时需注意这点。
 *
 * <p>java实现了自己的异步方法调用模式 =》FutureTask,
 * CompletableFuture 和 ExecutorService 是现实中该模式的实现。
 *
 * 这个例子只是为了演示和便于理解异步编程模式，并没有考虑到所有的使用场景
 *
 * @see AsyncResult
 * @see AsyncCallback
 * @see AsyncExecutor
 * @see java.util.concurrent.FutureTask
 * @see java.util.concurrent.CompletableFuture
 * @see java.util.concurrent.ExecutorService
 */
public class App {

  public static void main(String[] args) throws Exception {
    // construct a new executor that will run async tasks
    ThreadAsyncExecutor executor = new ThreadAsyncExecutor();

    // start few async tasks with varying processing times, two last with callback handlers
    final AsyncResult asyncResult1 = executor.startProcess(lazyval(10, 500));
    final AsyncResult asyncResult2 = executor.startProcess(lazyval("test", 300));
    final AsyncResult asyncResult3 = executor.startProcess(lazyval(50L, 700));


    final AsyncResult asyncResult4 = executor.startProcess(lazyval(20, 400), callback("Callback result 4"));
    final AsyncResult asyncResult5 = executor.startProcess(lazyval("callback", 600), callback("Callback result 5"));

    // emulate processing in the current thread while async tasks are running in their own threads
    Thread.sleep(350); // Oh boy I'm working hard here
    log("Some hard work done");

    // wait for completion of the tasks
    final Object result1 = executor.endProcess(asyncResult1);
    final Object result2 = executor.endProcess(asyncResult2);
    final Object result3 = executor.endProcess(asyncResult3);
    asyncResult4.await();
    asyncResult5.await();

    // log the results of the tasks, callbacks log immediately when complete
    log("Result 1: " + result1);
    log("Result 2: " + result2);
    log("Result 3: " + result3);
  }

  /**
   * 返回一个Callable用延迟来模拟任务
   */
  private static <T> Callable<T> lazyval(T value, long delayMillis) {
    return () -> {
      Thread.sleep(delayMillis);
      log("Task completed with: " + value);
      return value;
    };
  }

  /**
   * Creates a simple callback that logs the complete status of the async result.
   *
   * @param name callback name
   * @return new async callback
   */
  private static <T> AsyncCallback<T> callback(String name) {
    return (value, ex) -> {
      if (ex.isPresent()) {
        log(name + " failed: " + ex.map(Exception::getMessage).orElse(""));
      } else {
        log(name + ": " + value);
      }
    };
  }

  private static void log(String msg) {
    System.out.println(msg);
  }
}
