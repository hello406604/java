package com.kaishengit.com.kaishengit.daili;

import javax.jws.Oneway;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class ComputerInvocationHandler implements InvocationHandler {

    private Computer computer = null;
    public ComputerInvocationHandler (Computer computer) {
        this.computer = computer;
    }
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("前置通知");
        Object object = method.invoke(computer,args);
        System.out.println("后置通知");
        return object;
    }
}
