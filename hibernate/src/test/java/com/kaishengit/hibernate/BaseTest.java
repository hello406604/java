package com.kaishengit.hibernate;

import com.kaishengit.util.HibernateUtils;
import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;

public class BaseTest {

    protected Session session;
    @Before
    public void before() {
        session = HibernateUtils.getSession();
        session.beginTransaction();
    }
    @After
    public void after() {
        session.getTransaction().commit();
    }
}
