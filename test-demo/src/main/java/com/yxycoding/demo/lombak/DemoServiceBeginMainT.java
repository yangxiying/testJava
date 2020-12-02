package com.yxycoding.demo.lombak;/*
 * @author yangxy
 * @date 2020/11/4 14:15
 */

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

public class DemoServiceBeginMainT {

//     @Autowired
//     DemoServiceBegin demoServiceBegin;

    @Autowired
    DemoService2 demoService2;

    public void demoss(){

    }

    public static void main(String[] args) {
        DemoServiceBegin demoServiceBegin = new DemoServiceBegin(new DemoService1(new DemoService2()));
        demoServiceBegin.demoBegin("111111111");

        DemoService3 demoService3 = new DemoService3();



    }

}
