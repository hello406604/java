package com.kaishengit.crm.mapper;

import com.kaishengit.crm.entity.Customer;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CustomerMapper {
    List<Customer> findByParam(Map<String, Object> param);

    void save(Customer customer);

    Customer findById(@Param("id") Integer id);

    void update(Customer customer);

    void delById(@Param("id") Integer id);

    List<Customer> findByAccountId(@Param("accountId") Integer accountId);

    List<Map<String,Object>> findByLevelCount();

}
