package com.kaishengit.dao;

import com.kaishengit.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by 帅灏灏 on 2017/7/11.
 */
@Repository
public class UserDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public void save (User user) {
        String sql = "insert into t_user (user_name,password,address,com_id) values (?,?,?,?)";
        jdbcTemplate.update(sql,user.getUser_name(),user.getPassword(),user.getAddress(),user.getCom_id());
        throw new RuntimeException("eghiwegueiw");
    }

    public User findById(Integer id) {
        String sql = "select * from t_user where id = ?";
        return jdbcTemplate.queryForObject(sql,new RowMapper<User>() {

            @Override
            public User mapRow(ResultSet resultSet, int i) throws SQLException {
                User user = new User();
                user.setCom_id(resultSet.getInt("com_id"));
                user.setUser_name(resultSet.getString("user_name"));
                user.setAddress(resultSet.getString("address"));
                user.setPassword(resultSet.getString("password"));
                user.setId(resultSet.getInt("id"));
                return user;
            }
        },id);
    }

    public List<User> findAll() {
        String sql = "select * from t_user";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class));
    }

    public int count() {
        String sql = "select count(1) from t_user";
        return jdbcTemplate.queryForObject(sql,Integer.class);
    }

}
