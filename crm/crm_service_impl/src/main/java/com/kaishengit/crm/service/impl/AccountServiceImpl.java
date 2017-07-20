package com.kaishengit.crm.service.impl;

import com.kaishengit.crm.entity.Account;
import com.kaishengit.crm.entity.AccountDeptKey;
import com.kaishengit.crm.entity.AccountExample;
import com.kaishengit.crm.mapper.AccountDeptMapper;
import com.kaishengit.crm.mapper.AccountMapper;
import com.kaishengit.crm.service.AccountService;
import com.kaishengit.exception.AuthenticationException;
import com.kaishengit.exception.ServiceException;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by 帅灏灏 on 2017/7/17.
 */
@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountMapper accountMapper;
    @Autowired
    private AccountDeptMapper accountDeptMapper;

    @Value("password_salt")
    private String salt;

    @Override
    @Transactional
    public void saveAccount(Account account,Integer[] deptIds) {
        account.setCreateTime(new Date());
        account.setPassword(DigestUtils.md5Hex(salt + account.getPassword()));
        accountMapper.insert(account);
        System.out.println(account.getId());
        for (Integer deptId : deptIds) {
            AccountDeptKey accountDeptKey = new AccountDeptKey();
            accountDeptKey.setAccountId(account.getId());
            accountDeptKey.setDeptId(deptId);
            accountDeptMapper.insert(accountDeptKey);
        }

    }

    @Override
    public List<Account> findAllAccount() {
        return accountMapper.selectByExample(new AccountExample());
    }

    @Override
    public List<Account> findAllAccountByDeptName(Integer deptId) {
        return accountMapper.findAllAccountByDeptName(deptId);
    }

    @Override
    public Long countAll() {
        return accountMapper.countByExample(new AccountExample());
    }

    @Override
    public Long countByDeptId(Integer id) {
        if (new Integer(1).equals(id)) {
            id = null;
        }
        Long a = accountMapper.countByDeptId(id);
        System.out.println(a);
        return a;
    }

    @Override
    public List<Account> findByDeptId(Integer id) {
        if (new Integer(1).equals(id)) {
            id = null;
        }
        return accountMapper.findAllAccountByDeptId(id);
    }

    @Override
    @Transactional
    public int delById(Integer id) {
        accountDeptMapper.delById(id);
        return accountMapper.deleteById(id);
    }

    @Override
    public Account findAccountByMobile(String mobile,String password) {
        Account account = accountMapper.findAccountByMobile(mobile);
        if (account != null && account.getPassword().equals(DigestUtils.md5Hex(salt + password))){
            return account;
        } else {
            throw new AuthenticationException("账号或密码错误");
        }
    }

    @Override
    public void changePassword(String oldPassword, String password, Account account) {
        if (account.getPassword().equals(DigestUtils.md5Hex(salt + oldPassword))){
            account.setPassword(DigestUtils.md5Hex(salt + password));
            accountMapper.updateByPrimaryKeySelective(account);
        } else {
            throw  new ServiceException("原始密码输入错误,请重新输入");
        }
    }
}
