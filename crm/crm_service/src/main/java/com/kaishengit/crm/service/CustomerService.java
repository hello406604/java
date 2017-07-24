package com.kaishengit.crm.service;

import com.github.pagehelper.PageInfo;
import com.kaishengit.crm.entity.Account;
import com.kaishengit.crm.entity.Customer;

import java.io.OutputStream;
import java.util.List;
import java.util.Map;

public interface CustomerService {
    PageInfo<Customer> findByParam(Map<String, Object> param);

    /**
     *保存客户
     * @param customer
     */
    void save(Customer customer);

    List<String> findAllTrade();

    List<String> findAllSource();

    /**
     * 根据ID查找客户
     * @param id
     * @return
     */
    Customer findById(Integer id);

    /**
     * 修改客户信息
     * @param customer
     */
    void updateCustomer(Customer customer);

    /**
     * 根据id删除客户
     * @param id
     */
    void delById(Integer id);

    void pullPubicCustomer(Customer customer, Account account);

    void transferCustomer(Customer customer, Account account, Integer accountId);

    void exportAccountCustomerToExcel(Account account, OutputStream outputStream);

    void occupyCustomer(Customer customer, Account account);

    void exportPublicCustomerToExcel(Account account, OutputStream outputStream);
}
