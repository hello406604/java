package com.kaishengit.service;

import com.github.pagehelper.PageInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.kaishengit.entity.User;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:applicationContext.xml")
public class UserServiceTest {
	@Autowired
	UserService userService;
	@Test
	public void save() {
		User user = new User();
		user.setAddress("德国");
		user.setPassword("31344");
		user.setUserName("李萌");
		user.setComId(1);
		userService.save(user);
	}

	@Test
	public void findById() {
		Assert.assertNotNull(userService.findById(27));
	}

	@Test
	public void findByPage() {
		PageInfo<User> userPageInfo = userService.findAll(1,3);
		List<User> userList =userPageInfo.getList();
		for(User user : userList) {
			System.out.println(user.getUserName());
		}
	}
}
