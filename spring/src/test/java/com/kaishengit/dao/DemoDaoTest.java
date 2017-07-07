package com.kaishengit.dao;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.*;

/**
 * Created by 帅灏灏 on 2017/7/7.
 */
public class DemoDaoTest {
    @Test
    public void demo() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        DemoDao demoDao = (DemoDao) applicationContext.getBean("demoDao");
        demoDao.show();
    }

}