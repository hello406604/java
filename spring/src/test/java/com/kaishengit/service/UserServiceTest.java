package com.kaishengit.service;

import com.kaishengit.entity.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by 帅灏灏 on 2017/7/11.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class UserServiceTest {
    @Autowired
    UserService userService;
    @Test
    public void save() {
        User user = new User();
        user.setPassword("123123");
        user.setAddress("河南");
        user.setUser_name("ahudf");
        user.setCom_id(1);
        userService.save(user);
    }

    @Test
    public void findById() {
        Assert.assertNotNull(userService.findById(26));
    }

    @Test
    public void findAll() {
        List<User> userList = userService.findAll();
        for(User user : userList) {
            System.out.println(user.getUser_name() + "->" + user.getAddress());
        }
    }

    @Test
    public void count() {
        Assert.assertEquals(3,userService.count());
    }
}