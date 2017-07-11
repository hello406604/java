package com.kaishengit.service;

import com.kaishengit.dao.UserDao;
import com.kaishengit.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by 帅灏灏 on 2017/7/11.
 */
@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    @Transactional
    public void save(User user){
        userDao.save(user);
        userDao.save(user);
    }

    public User findById(Integer id) {
        return userDao.findById(id);
    }

    public List<User> findAll() {
        return userDao.findAll();
    }

    public int count() {
        return userDao.count();
    }
}
