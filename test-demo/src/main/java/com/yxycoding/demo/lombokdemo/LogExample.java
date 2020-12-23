package com.yxycoding.demo.lombokdemo;
/*
 * @author yangxy
 * @date 2020/12/23 14:35
 * 使用@Log注解，可以直接生成日志对象log，通过log对象可以直接打印日志
 */

import lombok.extern.java.Log;

@Log
public class LogExample {
    public static void main(String[] args) {
        log.info("level info");
        log.warning("level warning");
        log.severe("level severe");
    }
}
