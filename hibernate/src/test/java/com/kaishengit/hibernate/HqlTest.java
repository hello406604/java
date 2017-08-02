package com.kaishengit.hibernate;

import com.kaishengit.pojo.Account;
import com.kaishengit.util.HibernateUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class HqlTest {

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
        String hql = "from Account where id = :id";
        Query query = session.createQuery(hql);
        query.setParameter("id",3);
        Account account = (Account) query.uniqueResult();
        System.out.println(account);
    }

    @Test
    public void findBy () {
        String hql = "select userName, address, age from Account where id = :id";
        Query query = session.createQuery(hql);
        query.setParameter("id",3);
        Object[] objects = (Object[]) query.uniqueResult();
        for (Object obj : objects) {
            System.out.println(obj);
        }
    }

    @Test
    public void  count() {
        String hql = "select count(*) from Account";
        Query query = session.createQuery(hql);
        Long count = (Long) query.uniqueResult();
        System.out.println(count);
    }

    @Test
    public void findByUserNameDesc() {
        String hql = "select id,userName,address,age from Account where userName = :userName order by id desc";
        Query query = session.createQuery(hql);
        query.setParameter("userName","tom");
        List<Object[]> objects = query.list();
        for (Object[] objs : objects) {
            System.out.println(objs[0] + " ---> " + objs[1] + " ---> " + objs[2] + " ---> " + objs[3]);
        }
    }

    @Test
    public void findByUserNamePage() {
        String hql = "from Account where userName = :name order by id desc";
        Query query =session.createQuery(hql);
        query.setParameter("name","tom");
        query.setFirstResult(0);
        query.setMaxResults(2);
        List<Account> accounts = query.list();
        for (Account account : accounts) {
            System.out.println(account);
        }
    }
}
