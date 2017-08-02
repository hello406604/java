package com.kaishengit.hibernate;

import com.kaishengit.pojo.Account;
import com.kaishengit.util.HibernateUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.junit.After;
import org.junit.Before;

import java.util.List;

public class Test {

    @org.junit.Test
    public void save() {
        Configuration configuration = new Configuration().configure();
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties()).build();
        SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Account account = new Account();
        account.setAddress("上海");
        account.setUserName("tom");
        account.setAge(28);
        session.save(account);
        session.getTransaction().commit();

    }

    Session session ;

    @Before
    public void before() {
        session = HibernateUtils.getSession();
        session.getTransaction().begin();
    }

    @org.junit.Test
    public void findById() {
        Account account = (Account) session.get(Account.class,1);
        System.out.println(account.getUserName());
    }

    @org.junit.Test
    public void update() {
        Account account = (Account) session.get(Account.class,1);
        account.setUserName("lily");
    }

    @org.junit.Test
    public void delete() {
        Account account = (Account) session.get(Account.class,1);
        session.delete(account);
    }

    @org.junit.Test
    public void findAll() {
        String HQL = "from Account";
        List<Account> accounts = session.createQuery(HQL).list();
        for (Account a : accounts) {
            System.out.println(a.getUserName() + " ---> " + a.getAddress());
        }

        String HQl = "from Account where id=2";
        Account account = (Account) session.createQuery(HQl).list().get(0);
        System.out.println(account.getUserName());
    }

    @org.junit.Test
    public void findByParam() {
        String HQL = "from Account where userName = ?";
        Query query = session.createQuery(HQL);
        query.setParameter(0,"tom");
        Account account = (Account) query.list().get(0);
        System.out.println(account.getUserName() + "--->" + account.getAddress());
    }

    @After
    public void after() {
        session.getTransaction().commit();
    }
}
