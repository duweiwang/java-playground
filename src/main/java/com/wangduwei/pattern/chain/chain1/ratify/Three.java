package com.wangduwei.pattern.chain.chain1.ratify;

import com.wangduwei.pattern.chain.chain1.Chain;
import com.wangduwei.pattern.chain.chain1.Ratify;
import com.wangduwei.pattern.chain.chain1.Request;
import com.wangduwei.pattern.chain.chain1.Result;

public class Three implements Ratify {

    @Override
    public Result deal(Chain chain) {
        Request request = chain.request();
        System.out.println("DepartmentHeader=====>request:" + request.toString());
        if (request.getDays() > 7) {
            return new Result(false, "你这个完全没必要");
        }
        return new Result(true, "DepartmentHeader：不要着急，把事情处理完再回来！");
    }
}
