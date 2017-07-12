package com.kaishengit.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kaishengit.entity.User;
import com.kaishengit.mapper.UserMapper;

import java.util.List;

@Service
public class UserService {
	@Autowired
	UserMapper userMapper;
	public void save (User user){
		userMapper.save(user);
	}
	public User findById(int id){
		return userMapper.findById(id);
	}
	public PageInfo<User> findAll(int pageNum,int pageSize) {
		PageHelper.startPage(pageNum,pageSize);
		List<User> userList = userMapper.findAll();
		return new PageInfo<User>(userList);
	}
}
