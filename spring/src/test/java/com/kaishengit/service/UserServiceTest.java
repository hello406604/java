package com.kaishengit.service;

import com.kaishengit.dao.UserDao;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.*;

/**
 * Created by 帅灏灏 on 2017/7/7.
 */
public class UserServiceTest {

    @Test
    public void  save() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserDao userDao = (UserDao) applicationContext.getBean("userDao");
        UserService userService = (UserService) applicationContext.getBean("userService");
        userService.save();
    }
}