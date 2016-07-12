package com.j2web.web.test.aop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * the logBefore() method will be executed before the execution of customerBo interface, addCustomer() method.
 * Created by admin on 2016/3/22.
 */
@Aspect
@Component
public class LoggingAspect {

    // com.j2web.web.test 包及所有子包下任何类的任何方法
    @Before("execution(* com.j2web.web.test..*.*(..))")
    public void logBefore(JoinPoint joinPoint) {

        System.out.println("logBefore() is running!");
        System.out.println("hijacked: " + joinPoint.getSignature().getName());
        System.out.println("***********************");
    }

    @After("execution(* com.j2web.web.test.aop.CustomerAOP.addCustomer(..))")
    public void logAfter(JoinPoint joinPoint) {

        System.out.println("logAfter() is running!");
        System.out.println("hijacked: " + joinPoint.getSignature().getName());
        System.out.println("***********************");
    }

    @AfterReturning(
            pointcut = "execution(* com.j2web.web.test.aop.CustomerAOP.addCustomerReturnValue(..))",
            returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {

        System.out.println("logAfterReturning() is running!");
        System.out.println("hijacked : " + joinPoint.getSignature().getName());
        System.out.println("Method returned value is : " + result);
        System.out.println("******");

    }

    @AfterThrowing(
            pointcut = "execution(* com.j2web.web.test.aop.CustomerAOP.addCustomerThrowException(..))",
            throwing = "error")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable error) {

        System.out.println("logAfterThrowing() is running!");
        System.out.println("hijacked : " + joinPoint.getSignature().getName());
        System.out.println("Exception : " + error);
        System.out.println("******");

    }

    @Around("execution(* com.j2web.web.test.aop.CustomerAOP.addCustomerAround(..))")
    public void logAround(ProceedingJoinPoint joinPoint) throws Throwable {

        System.out.println("logAround() is running!");
        System.out.println("hijacked method : " + joinPoint.getSignature().getName());
        System.out.println("hijacked arguments : " + Arrays.toString(joinPoint.getArgs()));

        System.out.println("Around before is running!");
        joinPoint.proceed(); //continue on the intercepted method
        System.out.println("Around after is running!");

        System.out.println("******");

    }

}
