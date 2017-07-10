package com.kaishengit.mapper;

import com.kaishengit.entity.User;
import com.sun.xml.internal.ws.api.fastinfoset.FastInfosetFeature;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by 帅灏灏 on 2017/7/6.
 */
@CacheNamespace
public interface UserMapper {

    User findById(Integer id);

    @Delete("delete from t_user where id = #{id}")
    void delById(Integer id);

    @Insert("insert into t_user (user_name,password,address,com_id) values(#{userName},#{password},#{address},#{comId})")
    @Options(useGeneratedKeys = true,keyProperty = "id",flushCache = Options.FlushCachePolicy.FALSE)
    void save(User user);

    User findByParam(@Param("name")String userName ,@Param("password") String password);


    User findByList(@Param("list") List<Integer> list);

    @Select("select id,user_name,password,address,com_id from t_user")
    @Options(useCache=false)
    List<User> find();
}
