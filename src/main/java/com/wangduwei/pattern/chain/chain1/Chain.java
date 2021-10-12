package com.wangduwei.pattern.chain.chain1;

/**
 * <p>
 *
 * @author : wangduwei
 * @since : 2020/3/18  16:23
 **/
public interface Chain {
    // 获取当前request
    Request request();

    // 转发request
    Result proceed(Request request);
}
