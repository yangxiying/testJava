package com.yxycoding.demo.async;

import com.yxycoding.demo.lombak.DemoService1;
import com.yxycoding.demo.lombak.DemoService2;
import com.yxycoding.demo.lombak.DemoServiceBegin;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class AsyncTestTest {
    @Autowired
    AsyncTest asyncTest;

    @Autowired
    private DemoServiceBegin demoServiceBegin;

    @Autowired
    private DemoService1 demoService1;

    @Autowired
    private DemoService2 demoService2;

    @Test
    void test1() throws InterruptedException, ExecutionException {

        System.out.println("===0:"+System.currentTimeMillis());
        asyncTest.test1(10);
        System.out.println("===1:"+System.currentTimeMillis());
        asyncTest.test1(5);
        System.out.println("===2:"+System.currentTimeMillis());
        asyncTest.test2(9);
        System.out.println("===3:"+System.currentTimeMillis());
        asyncTest.test2(1);
        System.out.println("===4:"+System.currentTimeMillis());
        Future<String> stringFuture1 = asyncTest.testRet(7);
        System.out.println("===5:"+System.currentTimeMillis());
        Future<String> stringFuture2 = asyncTest.testRet(6);
        System.out.println("===6:"+System.currentTimeMillis());

        while (true){
            if(stringFuture1.isDone() && stringFuture2.isDone()){
                break;//任务全部执行完成
            }
        }
        System.out.println("===7:"+System.currentTimeMillis());
        System.out.println(stringFuture1.get());
        System.out.println(stringFuture2.get());
        System.out.println("===8:"+System.currentTimeMillis());
        TimeUnit.SECONDS.sleep(10);
        System.out.println("===9:"+System.currentTimeMillis());
    }
}