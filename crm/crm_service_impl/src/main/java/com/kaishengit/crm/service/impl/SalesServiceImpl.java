package com.kaishengit.crm.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kaishengit.crm.entity.Customer;
import com.kaishengit.crm.entity.SalesRecord;
import com.kaishengit.crm.mapper.SalesMapper;
import com.kaishengit.crm.service.SalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SalesServiceImpl implements SalesService {
    @Autowired
    private SalesMapper salesMapper;

    @Value("#{'${sales.progress}'.split(',')}")
    private List<String> currentProgress;

    /**
     * 根据ID现实当前员工的销售机会
     * @param accountId
     * @return
     */
    @Override
    public PageInfo<SalesRecord> showAllByAccId(Integer accountId, Integer p) {
        PageHelper.startPage(p,10);
        List<SalesRecord> salesRecords = salesMapper.showAllByAccId(accountId);
        for(SalesRecord salesRecord : salesRecords) {
            System.out.println ( salesRecord.getSalesName() + " == >" + salesRecord.getLastTime() + "--->" +salesRecord.getCurrentProgress() + "---->" +salesRecord.getCreateTime());
        }
        return new PageInfo<>(salesRecords);
    }

    /**
     * 获取配置文件中的当前进度
     * @return
     */
    public List<String> getCurrentProgress() {
        return currentProgress;
    }

    @Override
    public void saveSales(SalesRecord salesRecord,Integer accountId,Integer customerId) {
        System.out.println(salesRecord.getSalesName());
        Customer customer = new Customer();
        customer.setId(customerId);
        salesRecord.setCustomer(customer);
        salesRecord.setAccountId(accountId);
        salesRecord.setCreateTime(new Date());
        salesRecord.setLastTime(new Date());
        salesMapper.saveSales(salesRecord);

    }

    @Override
    public SalesRecord findById(Integer id) {
        return salesMapper.findById(id);
    }
}
