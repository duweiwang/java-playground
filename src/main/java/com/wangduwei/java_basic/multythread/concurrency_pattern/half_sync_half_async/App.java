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

package com.wangduwei.java_basic.multythread.concurrency_pattern.half_sync_half_async;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * This application demonstrates Half-Sync/Half-Async pattern. Key parts of the pattern are {@link
 * AsyncTask} and {@link AsynchronousService}.
 *
 * <p><i>PROBLEM</i> <br>
 *     并发系统的任务时长有长、中、短。中长时间的任务应该异步执行来满足服务的质量要求
 * A concurrent system have a mixture of short duration, mid duration and long duration tasks. Mid
 * or long duration tasks should be performed asynchronously to meet quality of service
 * requirements.
 *
 * <p><i>INTENT</i> <br>
 * The intent of this pattern is to separate the the synchronous and asynchronous processing in the
 * concurrent application by introducing two intercommunicating layers - one for sync and one for
 * async. This simplifies the programming without unduly affecting the performance.
 *
 * <p><i>APPLICABILITY</i> <br>
 * UNIX network subsystems - In operating systems network operations are carried out asynchronously
 * with help of hardware level interrupts.<br> CORBA - At the asynchronous layer one thread is
 * associated with each socket that is connected to the client. Thread blocks waiting for CORBA
 * requests from the client. On receiving request it is inserted in the queuing layer which is then
 * picked up by synchronous layer which processes the request and sends response back to the
 * client.<br> Android AsyncTask framework - Framework provides a way to execute long running
 * blocking calls, such as downloading a file, in background threads so that the UI thread remains
 * free to respond to user inputs.<br>
 *
 * <p><i>IMPLEMENTATION</i> <br>
 * The main method creates an asynchronous service which does not block the main thread while the
 * task is being performed. The main thread continues its work which is similar to Async Method
 * Invocation pattern. The difference between them is that there is a queuing layer between
 * Asynchronous layer and synchronous layer, which allows for different communication patterns
 * between both layers. Such as Priority Queue can be used as queuing layer to prioritize the way
 * tasks are executed. Our implementation is just one simple way of implementing this pattern, there
 * are many variants possible as described in its applications.
 */
public class App {

  public static void main(String[] args) {
    AsynchronousService service = new AsynchronousService(new LinkedBlockingQueue<>());
    /*
     * A new task to calculate sum is received but as this is main thread, it should not block. So
     * it passes it to the asynchronous task layer to compute and proceeds with handling other
     * incoming requests. This is particularly useful when main thread is waiting on Socket to
     * receive new incoming requests and does not wait for particular request to be completed before
     * responding to new request.
     */
    service.execute(new ArithmeticSumTask(1000));

    /*
     * 提交任务，并发执行
     */
    service.execute(new ArithmeticSumTask(500));
    service.execute(new ArithmeticSumTask(2000));
    service.execute(new ArithmeticSumTask(1));

    service.close();
  }

  /**
   * 求和算术
   */
  static class ArithmeticSumTask implements AsyncTask<Long> {
    private final long numberOfElements;

    public ArithmeticSumTask(long numberOfElements) {
      this.numberOfElements = numberOfElements;
    }

    /*
     * This is the long running task that is performed in background. In our example the long
     * running task is calculating arithmetic sum with artificial delay.
     */
    @Override
    public Long call() throws Exception {
      return ap(numberOfElements);
    }

    /*
     * This will be called in context of the main thread where some validations can be done
     * regarding the inputs. Such as it must be greater than 0. It's a small computation which can
     * be performed in main thread. If we did validated the input in background thread then we pay
     * the cost of context switching which is much more than validating it in main thread.
     */
    @Override
    public void onPreCall() {
      if (numberOfElements < 0) {
        throw new IllegalArgumentException("n is less than 0");
      }
    }

    @Override
    public void onPostCall(Long result) {
      // Handle the result of computation
      System.out.println(result.toString());
    }

    @Override
    public void onError(Throwable throwable) {
      throw new IllegalStateException("Should not occur");
    }
  }

  private static long ap(long i) {
    try {
      Thread.sleep(i);
    } catch (InterruptedException e) {
      System.out.println("Exception caught."+ e);
    }
    return i * (i + 1) / 2;
  }
}
