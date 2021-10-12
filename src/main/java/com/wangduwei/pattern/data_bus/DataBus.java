/**
 * The MIT License
 * Copyright (c) 2014-2016 Ilkka Seppälä
 * <p>
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * <p>
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * <p>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.wangduwei.pattern.data_bus;


import com.wangduwei.pattern.data_bus.data.DataType;
import com.wangduwei.pattern.data_bus.members.Member;

import java.util.HashSet;
import java.util.Set;

/**
 * 总线单例
 */
public class DataBus {

    private static final DataBus INSTANCE = new DataBus();

    private final Set<Member> listeners = new HashSet<>();

    public static DataBus getInstance() {
        return INSTANCE;
    }

    public void subscribe(final Member member) {
        this.listeners.add(member);
    }

    public void unsubscribe(final Member member) {
        this.listeners.remove(member);
    }

    public void publish(final DataType event) {
        event.setDataBus(this);
        listeners.forEach(listener -> listener.accept(event));
    }
}
