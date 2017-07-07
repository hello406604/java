package com.kaishengit.dao;

import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.jws.soap.SOAPBinding;

/**
 * Created by 帅灏灏 on 2017/7/7.
 */
public class UserDaoTest {
    @Test
    public void save() {
        // 1 创建spring容器
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        // 2 获取bean
        UserDao userDao = (UserDao) applicationContext.getBean("user");
        UserDao userDao1 = (UserDao) applicationContext.getBean("userDao");
        userDao.save();
        userDao1.save();
        applicationContext.close();
    }
}
