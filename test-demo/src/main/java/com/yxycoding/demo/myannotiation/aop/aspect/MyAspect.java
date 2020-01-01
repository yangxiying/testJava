package com.yxycoding.demo.myannotiation.aop.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class MyAspect {

    @Pointcut("@annotation(com.yxycoding.demo.myannotiation.MyAnnotationOne)")
    private void cut(){
    }

    // 前置通知
    @Before("cut()")
    public void BeforeCall() {
        log.info("====前置通知start");

        log.info("====前置通知end");
    }

    // 环绕通知
    @Around(value = "cut()")
    public Object AroundCall(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("====环绕通知start");

        // 注解所切的方法所在类的全类名
        String typeName = joinPoint.getTarget().getClass().getName();
        log.info("目标对象:[{}]", typeName);

        // 注解所切的方法名
        String methodName = joinPoint.getSignature().getName();
        log.info("所切方法名:[{}]", methodName);

        StringBuilder sb = new StringBuilder();
        // 获取参数
        Object[] arguments = joinPoint.getArgs();
        for (Object argument : arguments) {
            sb.append(argument.toString());
        }
        log.info("所切方法入参：[{}]", sb.toString());

        // 统计方法执行时间
        long start = System.currentTimeMillis();

        //执行目标方法，并获得对应方法的返回值
        Object result = joinPoint.proceed();
        log.info("返回结果:[{}]", result);

        long end = System.currentTimeMillis();
        log.info("====执行方法共用时：[{}]", (end - start));

        log.info("====环绕通知之结束");
        return result;
    }

    // 后置通知
    @After("cut()")
    public void AfterCall() {
        log.info("====后置通知start");

        log.info("====后置通知end");
    }

    // 最终通知
    @AfterReturning("cut()")
    public void AfterReturningCall() {
        log.info("====最终通知start");

        log.info("====最终通知end");
    }

    // 异常通知
    @AfterThrowing(value = "cut()", throwing = "ex")
    public void afterThrowing(Throwable ex) {
        throw new RuntimeException(ex);
    }
}
