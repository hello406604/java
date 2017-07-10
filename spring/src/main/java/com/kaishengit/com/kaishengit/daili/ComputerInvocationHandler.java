package com.kaishengit.com.kaishengit.daili;

import sun.security.x509.CertificatePolicyMap;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by 帅灏灏 on 2017/7/10.
 */
public class ComputerInvocationHandler implements InvocationHandler {
    private  Computer computer ;
    ComputerInvocationHandler (Computer computer) {
        this.computer=computer;
    }
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("前置通知");
        Object object = method.invoke(computer,args);
        System.out.println("后置通知");
        return object;
    }

}
