package com.kaishengit.aop;

/**
 * Created by 帅灏灏 on 2017/7/10.
 */
public class UserDao {
    public void exce () {
        System.out.println("异常演示");
        System.out.println(1/0);
    }
}
