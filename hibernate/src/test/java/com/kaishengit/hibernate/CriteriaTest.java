package com.kaishengit.hibernate;

import com.kaishengit.pojo.Account;
import com.kaishengit.util.HibernateUtils;
import com.sun.org.apache.regexp.internal.RE;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class CriteriaTest {

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
        Criteria criteria = session.createCriteria(Account.class);
        List<Account> accounts = criteria.list();
        for (Account account : accounts) {
            System.out.println(account);
        }
    }

    @Test
    public void findByUserName() {
        Criteria criteria = session.createCriteria(Account.class);
//        criteria.add(Restrictions.eq("userName","tom"));
//        criteria.add(Restrictions.eq("address","上海"));
//        criteria.add(Restrictions.le("id",5));
//        criteria.add(Restrictions.like("userName","t", MatchMode.START));
//        criteria.add(Restrictions.like("userName","o",MatchMode.ANYWHERE));
//        criteria.add(Restrictions.in("id",new Integer[]{2,3,5,6}));
//        criteria.add(Restrictions.or(Restrictions.eq("userName","lili"),Restrictions.eq("age",23)));

        //or 连接的另一个种写法 条件多时更加清晰 建议使用这个
        Disjunction disjunction = Restrictions.disjunction();
        disjunction.add(Restrictions.eq("userName","lili"));
        disjunction.add(Restrictions.eq("age",23));
        criteria.add(disjunction);
        List<Account> accounts = criteria.list();
        for (Account account : accounts) {
            System.out.println(account);
        }
    }

    @Test
    public void page () {
        Criteria criteria = session.createCriteria(Account.class);
        criteria.add(Restrictions.eq("userName","tom"));
        criteria.setFirstResult(2);
        criteria.setMaxResults(2);
        criteria.addOrder(Order.desc("id"));
        List<Account> accounts = criteria.list();
        for (Account account : accounts) {
            System.out.println(account);
        }
    }

    @Test
    public void count() {
        Criteria criteria = session.createCriteria(Account.class);
//        criteria.setProjection(Projections.count("id"));  // count(id) 根据id类进行聚合 如果id值为null 则不会count
//        criteria.setProjection(Projections.rowCount());   // count(*)  根据行数聚合  某一个列为null 没有影响
//        criteria.setProjection(Projections.max("id"));    // max(id)

        ProjectionList projectionList = Projections.projectionList();
        projectionList.add(Projections.rowCount());
        projectionList.add(Projections.max("id"));
        projectionList.add(Projections.min("age"));
        criteria.setProjection(projectionList);
        Object[] objects = (Object[]) criteria.uniqueResult();
        System.out.println("count : " + objects[0]);
        System.out.println("maxId : " + objects[1]);
        System.out.println("minAge : " + objects[2]);
//        Long count = (Long) criteria.uniqueResult();
//        System.out.println(count);
    }

}
