package com.wangduwei.pattern.chain.chain1;

import com.wangduwei.pattern.chain.chain1.ratify.Three;
import com.wangduwei.pattern.chain.chain1.ratify.One;
import com.wangduwei.pattern.chain.chain1.ratify.Two;

import java.util.ArrayList;
import java.util.List;

public class App {

    public static void main(String[] args) {
        //1.构建责任链
        List<Ratify> ratifies = new ArrayList<>();
        ratifies.add(new One());
        ratifies.add(new Two());
        ratifies.add(new Three());
        //构建请求对象
        Request request = new Request.Builder()
                .setDays(7)
                .setName("aaa")
                .setReason("生病")
                .build();
        //组装处理
        RealChain realChain = new RealChain(ratifies, request, 0);
        Result result = realChain.proceed(request);

    }

}
