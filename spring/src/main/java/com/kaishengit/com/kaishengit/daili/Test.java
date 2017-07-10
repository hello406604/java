package com.kaishengit.com.kaishengit.daili;

import java.lang.reflect.Proxy;

/**
 * Created by 帅灏灏 on 2017/7/10.
 */
public class Test {
    public static void main(String[] args) {
        dell dell =  new dell();
        ComputerInvocationHandler computerInvocationHandler = new ComputerInvocationHandler(dell);
        Computer computer = (Computer) Proxy.newProxyInstance(dell.getClass().getClassLoader(),dell.getClass().getInterfaces(),computerInvocationHandler);
        computer.sale();
    }
}
