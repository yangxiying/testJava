package com.yxycoding.demo.myannotiation.normal;

import com.yxycoding.demo.myannotiation.MyAnnotationOne;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MyStudent {

    //使用注解，如果无默认值的，必须赋值：有默认值的可以重新赋值
    @MyAnnotationOne(name = "yxy coding", arr = {23, 11, 23})
    public void study(int times) {
        for (int i = 0; i < times; i++) {
            log.info("Good Good Study, Day Day Up!");
        }
    }
}
