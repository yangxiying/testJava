package com.yxycoding.demo.lombak;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.*;

/*
 * @author yangxy
 * @date 2020/11/4 14:45
 */
@SpringBootTest
public class DemoServiceBeginTest {

    @Autowired
    private DemoServiceBegin demoServiceBegin;

    @Autowired
    private DemoService1 demoService1;

    @Autowired
    private DemoService2 demoService2;


    @Test
    public void demoBegin() {
//        demoServiceBegin.demoBegin("55555555");

        demoService1.demo();
    }
}