package com.kaishengit.daili2;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by 帅灏灏 on 2017/7/10.
 */
public class MouseMethodIntercepter implements MethodInterceptor {
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("前置通知");
        Object result = methodProxy.invokeSuper(o,objects);
        System.out.println("后置通知");
        return result;
    }
}
