package com.kaishengit.crm.service;

import com.kaishengit.crm.entity.Account;

import java.util.List;

/**
 * Created by 帅灏灏 on 2017/7/17.
 */
public interface AccountService {

    void saveAccount(Account account);

    List<Account> findAllAccount();
}