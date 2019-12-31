package com.yxycoding.demo.async;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

@Component
public class AsyncTest {



    @Async
    public void test1(long timeSleep) throws InterruptedException {
        System.out.println("开始test2:=="+timeSleep);
        long startTime = System.currentTimeMillis();
        TimeUnit.SECONDS.sleep(timeSleep);
        long endTime= System.currentTimeMillis();
        System.out.println("task1耗时："+(endTime-startTime) );
    }

    @Async
    public void test2(long timeSleep) throws InterruptedException {
        System.out.println("开始test2:=="+timeSleep);
        long startTime = System.currentTimeMillis();
        TimeUnit.SECONDS.sleep(timeSleep);
        long endTime= System.currentTimeMillis();
        System.out.println("test2耗时："+(endTime-startTime) );
    }

    @Async
    public Future<String> testRet(long timeSleep) throws InterruptedException {
        System.out.println("开始testRet:=="+timeSleep);
        long startTime = System.currentTimeMillis();
        TimeUnit.SECONDS.sleep(timeSleep);
        long endTime= System.currentTimeMillis();
        System.out.println("testRet耗时："+(endTime-startTime) );
        return new AsyncResult<>("testRet返回耗时："+(endTime-startTime));
    }

    public static void main(String[] args) throws InterruptedException {
        /**
         * 这样启不到异步的作用，springboot开启异步是在主main中加注解 @EnableAsync
         */
        AsyncTest asyncTest = new AsyncTest();
        asyncTest.test1(10);
        asyncTest.test1(5);
        asyncTest.test2(9);
        asyncTest.test2(1);
    }
}
