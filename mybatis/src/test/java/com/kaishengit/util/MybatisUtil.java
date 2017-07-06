package com.kaishengit.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;

/**
 * Created by 帅灏灏 on 2017/7/6.
 */
public class MybatisUtil {
    private static SqlSessionFactory sqlSessionFactory = buildSqlSessionFactory();

    public static SqlSessionFactory buildSqlSessionFactory(){
        Reader reader = null;
        try {
            reader = Resources.getResourceAsReader("mybatis.xml");
            return new  SqlSessionFactoryBuilder().build(reader);
        } catch (IOException e) {
            throw  new RuntimeException("创建SqlSessionFactory异常",e);
        }
    }

    public static SqlSessionFactory getSqlSessionFactory() {
        return  sqlSessionFactory;
    }

    public static SqlSession getSqlSession(){
        return sqlSessionFactory.openSession();
    }
}
