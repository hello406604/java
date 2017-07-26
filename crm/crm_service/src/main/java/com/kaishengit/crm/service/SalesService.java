package com.kaishengit.crm.service;

import com.github.pagehelper.PageInfo;
import com.kaishengit.crm.entity.SalesRecord;

import java.util.List;

public interface SalesService {
    PageInfo<SalesRecord> showAllByAccId(Integer id, Integer p);
    public List<String> getCurrentProgress();

    void saveSales(SalesRecord salesRecord,Integer accountId,Integer customerId);

    SalesRecord findById(Integer id);
}
