package com.wangduwei.pattern.chain.chain1.ratify;

import com.wangduwei.pattern.chain.chain1.Chain;
import com.wangduwei.pattern.chain.chain1.Ratify;
import com.wangduwei.pattern.chain.chain1.Request;
import com.wangduwei.pattern.chain.chain1.Result;

public class One implements Ratify {
    @Override
    public Result deal(Chain chain) {
        Request request = chain.request();
        System.out.println("GroupLeader=====>request:" + request.toString());

        if (request.getDays() > 1) {
            // 包装新的Request对象
            Request newRequest = new Request.Builder().newRequest(request)
                    .setManagerInfo(request.getName() + "平时表现不错，而且现在项目也不忙")
                    .build();
            return chain.proceed(newRequest);
        }
        return new Result(true, "GroupLeader：早去早回");
    }

}
