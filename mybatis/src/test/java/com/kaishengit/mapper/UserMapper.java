package com.kaishengit.mapper;

import com.kaishengit.entity.User;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by 帅灏灏 on 2017/7/6.
 */
@CacheNamespace
public interface UserMapper {
    User findById(Integer id);

    User findByParam(@Param("name")String userName ,@Param("password") String password);

    User findByList(@Param("list") List<Integer> list);

    @Select("select id,user_name,password,address,com_id from t_user")
    List<User> find();
}
