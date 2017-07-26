package com.kaishengit.crm.service;

import com.kaishengit.crm.entity.RecordSalesRecord;

public interface RescordSalesService {
    void save(String content, Integer id);

    void updateSaleChance(Integer id, String progress);

    void saveNewChance(RecordSalesRecord recordSalesRecord);
}
