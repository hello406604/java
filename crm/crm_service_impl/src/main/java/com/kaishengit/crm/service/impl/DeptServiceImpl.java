package com.kaishengit.crm.service.impl;

import com.kaishengit.crm.entity.Dept;
import com.kaishengit.crm.entity.DeptExample;
import com.kaishengit.crm.mapper.DeptMapper;
import com.kaishengit.crm.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 帅灏灏 on 2017/7/17.
 */
@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    DeptMapper deptMapper;
    @Override
    public List<Dept> findAll() {
        return deptMapper.selectByExample(new DeptExample());
    }

    @Override
    public int addDept(Dept dept) {
        return deptMapper.insert(dept);
    }
}
