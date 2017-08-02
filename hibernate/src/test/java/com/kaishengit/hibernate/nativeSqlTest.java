package com.kaishengit.hibernate;

import com.kaishengit.pojo.Account;
import com.kaishengit.util.HibernateUtils;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class nativeSqlTest {

    Session session ;
    @Before
    public void before() {
        session = HibernateUtils.getSession();
        session.getTransaction().begin();
    }
    @After
    public void after() {
        session.getTransaction().commit();
    }

    @Test
    public void findAll() {
        String sql = "select id,user_name,address,age from account WHERE user_name = ?";
        SQLQuery sqlQuery = session.createSQLQuery(sql).addEntity(Account.class);
        sqlQuery.setParameter(0,"tom");
        sqlQuery.setFirstResult(2);
        sqlQuery.setMaxResults(2);

        List<Account> accounts = sqlQuery.list();
        for (Account account : accounts) {
            System.out.println(account);
        }
//        List<Object[]> objects = sqlQuery.list();
//        for (Object[] objs : objects) {
//            System.out.println(objs[0] + "--->" + objs[1] + "--->" + objs[2] + "--->" + objs[3]);
//        }
    }

//    @Test
//    public void
}
