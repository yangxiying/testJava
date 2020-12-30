package com.yxycoding.demo.copyActiviti.impl.interceptor;/*
 * @author yangxy
 * @date 2020/12/2 11:15
 */

public interface CommandExecutor {

    <T> T execute(Command<T> command);
}
