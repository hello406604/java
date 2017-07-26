package com.kaishengit.crm.mapper;

import com.kaishengit.crm.entity.RecordSalesRecord;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

public interface RecordSalesMapper {

    void insert(RecordSalesRecord recordSalesRecord);
}
