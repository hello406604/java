package com.kaishengit.crm.service;

        import com.kaishengit.crm.entity.Account;

        import java.util.List;

/**
 * Created by 帅灏灏 on 2017/7/17.
 */
public interface AccountService {

    void saveAccount(Account account, Integer[] deptId);

    List<Account> findAllAccount();

    List<Account> findAllAccountByDeptName(Integer deptId);

    Long countAll();

    Long countByDeptId(Integer id);

    List<Account> findByDeptId(Integer id);

    int delById(Integer id);

    Account findAccountByMobile(String mobile,String password);

    void changePassword(String oldPassword, String password, Account account);
}