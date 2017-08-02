package com.kaishengit.util;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.dbutils.ResultSetHandler;

import com.kaishengit.pojo.Account;


public class AccountResultSetHandler implements ResultSetHandler<Account> {

    @Override
    public Account handle(ResultSet rs) throws SQLException {
        Account acc = null;
            acc = new Account();
            acc.setId(rs.getInt(1));
            acc.setUserName(rs.getString(2));
            acc.setAddress(rs.getString(3));
            acc.setAge(rs.getInt(4));
        return acc;
    }

}
