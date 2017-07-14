package com.kaishengit.mapper;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.kaishengit.entity.User;

import java.util.List;


public interface UserMapper {
	void save(User user);

	@Select("select * from t_user where id = #{id}")
	User findById(int id);

	@Select("select * from t_user")
	List<User> findAll();
}