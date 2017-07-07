package com.kaishengit.dao;

import java.util.*;

/**
 * Created by 帅灏灏 on 2017/7/7.
 */
public class DemoDao {
    private double score;
    private String name;
    private List<String> nameList;
    private Set<UserDao> userDaoSet;

    public void setScore(double score) {
        this.score = score;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNameList(List<String> nameList) {
        this.nameList = nameList;
    }

    public void setUserDaoSet(Set<UserDao> userDaoSet) {
        this.userDaoSet = userDaoSet;
    }

    public void setMySet(Map<String, Object> mySet) {
        this.mySet = mySet;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    private Map<String, Object> mySet;
    private Properties properties;

    public void show() {
        System.out.println(name);
        System.out.println(score);
        for (String n: nameList) {
            System.out.println(n);
        }
        for (UserDao u:userDaoSet) {
            System.out.println(u);
        }
        Set<Map.Entry<String, Object>> en = mySet.entrySet();
        for (Map.Entry<String,Object> n: en ) {
            System.out.println(n.getKey() + "->" + n.getValue());
        }

        Enumeration keys = properties.propertyNames();
        while (keys.hasMoreElements()) {
            String key = (String) keys.nextElement();
            String value = (String) properties.get(key);
            System.out.println(key + "==>" + value);
        }
    }
}
