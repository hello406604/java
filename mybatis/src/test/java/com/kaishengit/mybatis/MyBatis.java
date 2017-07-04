package com.kaishengit.mybatis;

import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.kaishengit.entity.User;

public class MyBatis {
	
	@Test
	public void first() throws Exception {
		Reader reader = Resources.getResourceAsReader("mybatis.xml");
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		SqlSession sqlSession = sqlSessionFactory.openSession();
		User user = new User();
		user.setAddress("焦作");
		user.setUserName("hello");
		user.setDeptId(3);
		sqlSession.insert("com.kaishengit.mapper.UserMapper.save", user);
		sqlSession.commit();
		sqlSession.close();
	}
	
	@Test
	public void findById() throws Exception {
		Reader reader = Resources.getResourceAsReader("mybatis.xml");
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		SqlSession sqlSession = sqlSessionFactory.openSession();
		User user = sqlSession.selectOne("com.kaishengit.mapper.UserMapper.findById",10);
		System.out.println(user);
		sqlSession.close();
	}
}
