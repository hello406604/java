package com.kaishengit.test;

import com.kaishengit.entity.User;
import com.kaishengit.mapper.UserMapper;
import com.kaishengit.util.MybatisUtil;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class MybatisTest {
	
	Logger logger = LoggerFactory.getLogger(MybatisTest.class);
	private UserMapper userMapper ;
	private SqlSession sqlSession;
	@Test
	public void first() {
		System.out.println("1");
	}

	@Before
	public void before(){
		sqlSession = MybatisUtil.getSqlSession();
		userMapper = sqlSession.getMapper(UserMapper.class);
	}

//	@After
//	public void after(){
//		sqlSession.close();
//	}

	@Test
	public void findById(){
		User user = userMapper.findById(25);
		System.out.print(user);
	}

	@Test
	public void findByParam(){
		User user = userMapper.findByParam("tom", "111111");
		User user2 = userMapper.findByParam("tom", "111111");
		System.out.println(user);
		System.out.println(user2);
		sqlSession.close();
		SqlSession sqlSession2 = MybatisUtil.getSqlSession();
		UserMapper userMapper1 = sqlSession2.getMapper(UserMapper.class);
		User user1 = userMapper1.findByParam("tom","111111");
		System.out.print(user1);
		sqlSession2.close();
	}

	@Test

	public void find() {
		List<User> userList = userMapper.find();
		for (User user: userList) {
			System.out.println(user);
		}
	}
}
