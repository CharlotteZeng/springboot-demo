package com.springbootdemo.aop;

import com.springbootdemo.annotation.LogAnnotation;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Date;

@Aspect
@Component
public class LogAopAspect {
    /**
     * around的annotation里的参数有两种方式一种是全限定名 一种是注入的变量名
     */

    @Around("@annotation(com.springbootdemo.annotation.LogAnnotation)")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("前置通知");
        Object proceed = null;
        try {
            System.out.println("正常通知");
            proceed=joinPoint.proceed();
        } catch (Throwable throwable) {
            System.out.println("异常通知");
        }
        System.out.println("最终通知");
        return proceed;
    }
    /*@Around("@annotation(logAnnotation)")
    public Object around1(ProceedingJoinPoint joinPoint,LogAnnotation logAnnotation) throws Throwable {
        System.out.println("前置通知");
        Object proceed = null;
        try {
            System.out.println("正常通知");
            proceed=joinPoint.proceed();
        } catch (Throwable throwable) {
            System.out.println("异常通知");
        }
        System.out.println("最终通知");
        return proceed;
    }*/
}
