package com.kaishengit.crm.service.impl;

import com.kaishengit.crm.entity.Customer;
import com.kaishengit.crm.entity.RecordSalesRecord;
import com.kaishengit.crm.entity.SalesRecord;
import com.kaishengit.crm.mapper.CustomerMapper;
import com.kaishengit.crm.mapper.RecordSalesMapper;
import com.kaishengit.crm.mapper.SalesMapper;
import com.kaishengit.crm.service.RescordSalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class RescordSalesServiceImpl implements RescordSalesService {

    @Autowired
    RecordSalesMapper recordSalesMapper;

    @Autowired
    SalesMapper salesMapper;

    @Autowired
    CustomerMapper customerMapper;

    @Override
    public void save(String content, Integer salesId) {
        Date date = new Date();
        RecordSalesRecord recordSalesRecord = new RecordSalesRecord();
        recordSalesRecord.setCreateTime(date);
        recordSalesRecord.setContent(content);
        recordSalesRecord.setSalesId(salesId);
        recordSalesMapper.insert(recordSalesRecord);
    }

    @Override
    @Transactional
    public void updateSaleChance(Integer id, String progress) {
        SalesRecord salesRecord = salesMapper.findById(id);
        salesRecord.setCurrentProgress(progress);
        salesMapper.update(salesRecord);
        salesRecord.getCurrentProgress();
        RecordSalesRecord recordSalesRecord = new RecordSalesRecord();
        recordSalesRecord.setCreateTime(new Date());
        recordSalesRecord.setSalesId(id);
        recordSalesRecord.setContent("将当前进度修改为：[ " + progress + " ]");
        saveNewChance(recordSalesRecord);
    }

    @Override
    @Transactional
    public void saveNewChance(RecordSalesRecord recordSalesRecord) {
        //添加跟进记录
        recordSalesRecord.setCreateTime(new Date());
        recordSalesMapper.insert(recordSalesRecord);
        // 修改销售机会的最后跟进时间
        SalesRecord salesRecord = salesMapper.findById(recordSalesRecord.getSalesId());
        salesRecord.setLastTime(new Date());
        salesMapper.update(salesRecord);
        //修改客户最后跟进时间
        Customer customer = customerMapper.findById(salesRecord.getCustId());
        customer.setLastContactTime(new Date());
        customerMapper.update(customer);
    }
}
