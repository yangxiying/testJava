package com.yxycoding.demo.copyActiviti.impl.interceptor;/*
 * @author yangxy
 * @date 2020/12/2 10:42
 */

public interface Command<T> {

    T execute(CommandContext commandContext);
}
