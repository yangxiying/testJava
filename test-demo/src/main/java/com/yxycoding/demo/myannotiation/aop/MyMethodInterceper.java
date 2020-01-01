package com.yxycoding.demo.myannotiation.aop;

import com.yxycoding.demo.myannotiation.MyAnnotationOne;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;


import java.lang.reflect.Method;

@Slf4j
public class MyMethodInterceper implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        log.info("MyMethodInterceper=============start");
        MyAnnotationOne myAnnotationOne = methodInvocation.getMethod().getAnnotation(MyAnnotationOne.class);
        if(null != myAnnotationOne){
            log.info("-------------name: " + myAnnotationOne.name() + ", age: " + myAnnotationOne.age()
                    + ", score: " + myAnnotationOne.arr()[0]);
        }

        Object object = methodInvocation.proceed();

        log.info("MyMethodInterceper=============end");
        return object;
    }


}
