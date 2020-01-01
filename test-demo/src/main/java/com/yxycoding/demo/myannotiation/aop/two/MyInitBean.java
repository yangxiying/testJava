package com.yxycoding.demo.myannotiation.aop.two;

import com.yxycoding.demo.myannotiation.MyAnnotationOne;
import com.yxycoding.demo.myannotiation.aop.MyMethodInterceper;
import org.aopalliance.aop.Advice;
import org.springframework.aop.Pointcut;
import org.springframework.aop.support.AbstractPointcutAdvisor;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.annotation.AnnotationMatchingPointcut;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MyInitBean  extends AbstractPointcutAdvisor implements InitializingBean {
    private Advice advice;
    private Pointcut pointcut;

    @Override
    public void afterPropertiesSet() throws Exception {
        this.pointcut = AnnotationMatchingPointcut.forMethodAnnotation(MyAnnotationOne.class);

        MyMethodInterceper myAnnotationMethodInterceptor = new MyMethodInterceper();
        DefaultPointcutAdvisor defaultPointcutAdvisor = new DefaultPointcutAdvisor(pointcut, myAnnotationMethodInterceptor);
        this.advice = defaultPointcutAdvisor.getAdvice();
        System.out.println("sssssss");
    }

    @Override
    public Pointcut getPointcut() {
        return this.pointcut;
    }

    @Override
    public Advice getAdvice() {
        return this.advice;
    }
}
