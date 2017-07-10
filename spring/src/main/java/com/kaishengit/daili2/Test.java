package com.kaishengit.daili2;

import net.sf.cglib.proxy.Enhancer;

/**
 * Created by 帅灏灏 on 2017/7/10.
 */
public class Test {
    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Mouse.class);
        enhancer.setCallback(new MouseMethodIntercepter());
        Mouse mouse = (Mouse) enhancer.create();
        mouse.move();
    }
}
