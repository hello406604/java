package com.kaishengit.aop;

/**
 * Created by 帅灏灏 on 2017/7/10.
 */
public class AopAspect {
    public void beforeAdvice(){
        System.out.println("前置通知");
    }
    public void afterAdvice(){
        System.out.println("后置通知");
    }
    public void exceptionAdvice(Exception e) {
        System.out.println("Exception  " + e.getMessage());
    }
}
