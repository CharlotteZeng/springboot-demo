package com.springbootdemo.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

//@Component
//@Aspect
public class LogAspect {
    /*@Pointcut("execution(public * com.springbootdemo.controller.*.*(..))")
    public void LogAspect(){}

    @Before("LogAspect()")
    public void doBefore(JoinPoint joinPoint){
        System.out.println("doBefore");
    }

    @After("LogAspect()")
    public void doAfter(JoinPoint joinPoint){
        System.out.println("doAfter");
    }

    @AfterReturning("LogAspect()")
    public void doAfterReturning(JoinPoint joinPoint){
        System.out.println("doAfterReturning");
    }

    @AfterThrowing("LogAspect()")
    public void deAfterThrowing(JoinPoint joinPoint){
        System.out.println("deAfterThrowing");
    }

    @Around("LogAspect()")
    public Object deAround(ProceedingJoinPoint joinPoint) throws Throwable{
        System.out.println("deAround");
        return joinPoint.proceed();
    }*/

    /*@Around("@annotation(com.springbootdemo.annotation.LogAnnotation)")
    public Object aroundAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("前置通知");
        Object proceed = null;
        try {
            System.out.println("正常通知");
            proceed=proceedingJoinPoint.proceed();
        } catch (Throwable throwable) {
            System.out.println("异常通知");
        }
            System.out.println("最终通知");
        return proceed;
    }*/
}
