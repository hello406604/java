package com.kaishengit.hibernate;

import com.kaishengit.pojo.Account;
import com.kaishengit.util.HomeWork;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomeTest {

    HomeWork homeWork = new HomeWork();

    @Test
    public void tet() {
        String sql = "select * from account where user_name = :userName and age = :age";
        Map<String,Object> map = new HashMap();
        map.put("userName","tom");
        map.put("age",23);
        try {
            List<Account> accounts = homeWork.findByParam(sql,map);
            for (Account account : accounts)
            System.out.println(account.getUserName() + " ---> " + account.getAddress() + " ---> " + account.getAge());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
