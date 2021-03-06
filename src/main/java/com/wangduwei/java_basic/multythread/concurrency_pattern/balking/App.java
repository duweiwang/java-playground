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

package com.wangduwei.java_basic.multythread.concurrency_pattern.balking;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 我的理解就是在做逻辑前做状态检查。
 *
 * 止步模式，在不合适的阶段调用方法将什么都不做。
 *
 * In Balking Design Pattern if an object’s method is invoked when it is in an inappropriate state,
 * then the method will return without doing anything. Objects that use this pattern are generally
 * only in a state that is prone to（倾向于） balking temporarily but for an unknown amount of time
 *
 * 下面展示洗衣机的例子，有enable和washing状态，如果洗衣机已经在洗衣服，那么将什么都不做。
 * <p>In this example implementation WashingMachine is an object that has two states in which it
 * can be: ENABLED and WASHING. If the machine is ENABLED the state is changed into WASHING that any
 * other thread can't invoke this action on this and then do the job. On the other hand if it have
 * been already washing and any other thread execute wash() it can't do that once again and returns
 * doing nothing.
 */

public class App {

  /**
   * Entry Point.
   *
   * @param args the command line arguments - not used
   */
  public static void main(String... args) {
    final WashingMachine washingMachine = new WashingMachine();
    ExecutorService executorService = Executors.newFixedThreadPool(3);
    for (int i = 0; i < 3; i++) {
      executorService.execute(washingMachine::wash);
    }
    executorService.shutdown();
    try {
      executorService.awaitTermination(10, TimeUnit.SECONDS);
    } catch (InterruptedException ie) {
      System.out.println("ERROR: Waiting on executor service shutdown!");
    }
  }

}
