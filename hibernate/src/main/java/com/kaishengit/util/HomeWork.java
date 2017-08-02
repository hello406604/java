package com.kaishengit.util;

import com.kaishengit.pojo.Account;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HomeWork {

    String sql = "select * from account where user_name= :userName and address = :address and age = :age";
    public List<Account> findByParam(String sql, Map<String, Object> param) throws Exception {
        //根据:来截取字符串
        String[] strings = sql.split(":");
        // 参数字符串集合  如userName , address , age
        String[] stringList = new String[10];
        for(int i = 0 ; i < strings.length ; i++) {
            System.out.println(strings[i]);
            if (i != 0 && i != strings.length-1) { // 当i=0 时 没有参数  当i=length-1时 就是参数
                // 当条件成立时 截取字符串 获取参数名称
                stringList[i-1] = strings[i].substring(0,strings[i].indexOf(" "));
            } else if(i == strings.length-1) {
                // 当为最后一个时  直接赋值
                stringList[i-1] = strings[i];
            }
        }
        // 循环参数字符串集合 将参数替换为 ?
        for (String str: stringList) {
            System.out.println(str);
            if(str != null) {
                sql = sql.replace(":" + str , "?");
            }
        }
        // 拼装sql成功
        System.out.println(sql);
        //获取数据库连接
        PreparedStatement statement = null;
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql:///test","root","root");
        statement = connection.prepareStatement(sql);
        //遍历参数集合中的值 对应的map集合中的值设置给prepareStatement;
        for (int i = 0;  i < stringList.length ; i++) {
            String str = stringList[i];
            if(str != null) {
                statement.setObject(i + 1,param.get(str));
            }
        }
        //将resultSet 封装成对象
        ResultSet resultSet =  statement.executeQuery();
        List<Account> accounts = new ArrayList<Account>();
        while (resultSet.next()) {
            Account account = new AccountResultSetHandler().handle(resultSet);
            accounts.add(account);
        }
        return accounts;
    }
}
