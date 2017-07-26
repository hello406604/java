package com.kaishengit.crm.mapper;

import com.kaishengit.crm.entity.SalesRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SalesMapper {
    List<SalesRecord> showAllByAccId(@Param("accountId") Integer accountId);

    void saveSales(SalesRecord salesRecord);

    SalesRecord findById(@Param("id") Integer id);

    void update(SalesRecord salesRecord);
}
