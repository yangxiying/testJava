package com.yxycoding.demo.myannotiation.aop.one;

import com.yxycoding.demo.myannotiation.aop.MyMethodInterceper;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.JdkRegexpMethodPointcut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InterceptorConfig {

//    public static final String traceExecution = "execution(* com.yxycoding.demo.myannotiation..*.*(..))";

    @Bean
    public DefaultPointcutAdvisor defaultPointcutAdvisor() {
        MyMethodInterceper interceptor = new MyMethodInterceper();

//        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
//        pointcut.setExpression(traceExecution);

        JdkRegexpMethodPointcut pointcut = new JdkRegexpMethodPointcut();
        pointcut.setPatterns("com.yxycoding.demo.myannotiation.aop.*");

        // 配置增强类advisor
        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor();
        advisor.setPointcut(pointcut);
        advisor.setAdvice(interceptor);
        return advisor;
    }
}
