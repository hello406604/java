package com.kaishengit.crm.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kaishengit.crm.entity.Account;
import com.kaishengit.crm.entity.Customer;
import com.kaishengit.crm.mapper.CustomerMapper;
import com.kaishengit.crm.service.CustomerService;
import com.kaishengit.exception.ServiceException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Value("#{'${customer.trade}'.split(',')}") //SpringEL
    private List<String> tradeList;
    @Value("#{'${customer.source}'.split(',')}")
    private List<String> sourceList;
    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public PageInfo<Customer> findByParam(Map<String, Object> param) {
        int pageNo = (Integer) param.get("pageNo");
        PageHelper.startPage(pageNo, 10);
        List<Customer> customers = customerMapper.findByParam(param);
        return new PageInfo<>(customers);
    }

    @Override
    public void save(Customer customer) {
        customerMapper.save(customer);
    }

    @Override
    public List<String> findAllTrade() {
        return tradeList;
    }

    @Override
    public List<String> findAllSource() {
        return sourceList;
    }

    @Override
    public Customer findById(Integer id) {
        return customerMapper.findById(id);
    }

    @Override
    public void updateCustomer(Customer customer) {
        customerMapper.update(customer);
    }

    @Override
    public void delById(Integer id) {
        customerMapper.delById(id);
    }

    @Override
    public void pullPubicCustomer(Customer customer, Account account) {
        customer.setAccountId(null);
        customer.setReminder("由 (" + account.getUserName() + ") 放入公海");
        customerMapper.update(customer);
    }

    @Override
    public void transferCustomer(Customer customer, Account account, Integer accountId) {
        customer.setAccountId(accountId);
        customer.setReminder("由 (" + account.getUserName() + ") 处转交");
        customerMapper.update(customer);
    }

    @Override
    public void exportAccountCustomerToExcel(Account account, OutputStream outputStream) {

        List<Customer> customerList = customerMapper.findByAccountId(account.getId());
        createWorkBook(customerList,outputStream);
    }

    @Override
    public void occupyCustomer(Customer customer, Account account) {
        customer.setAccountId(account.getId());
        customer.setReminder("由 (" + account.getUserName() + ") 放入公海");
        customerMapper.update(customer);
    }

    @Override
    public void exportPublicCustomerToExcel(Account account, OutputStream outputStream) {
        List<Customer> customerList = customerMapper.findByAccountId(null);
        createWorkBook(customerList,outputStream);
    }

    private void createWorkBook(List<Customer> customerList, OutputStream outputStream){
        //创建工作表
        Workbook workbook = new HSSFWorkbook();
        //创建sheet页
        Sheet sheet = workbook.createSheet("客户信息表");
        //创建数据
        Row row = sheet.createRow(0);
        row.createCell(0).setCellValue("客户名称");
        row.createCell(1).setCellValue("职位");
        row.createCell(2).setCellValue("级别");
        row.createCell(3).setCellValue("联系方式");

        for (int i = 0; i < customerList.size(); i++) {
            Customer customer = customerList.get(i);
            Row rowI = sheet.createRow(i + 1);
            rowI.createCell(0).setCellValue(customer.getCustName());
            rowI.createCell(1).setCellValue(customer.getJobTitle());
            rowI.createCell(2).setCellValue(customer.getLevel());
            rowI.createCell(3).setCellValue(customer.getMobile());
        }

        try {
            workbook.write(outputStream);
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            throw new ServiceException("导出Excel出错");
        }
    }
}
