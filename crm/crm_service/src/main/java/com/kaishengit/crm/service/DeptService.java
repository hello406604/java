package com.kaishengit.crm.service;

import com.kaishengit.crm.entity.Dept;

import java.util.List;

/**
 * Created by 帅灏灏 on 2017/7/17.
 */
public interface DeptService {

    List<Dept> findAll();

    int addDept(Dept dept);
}
