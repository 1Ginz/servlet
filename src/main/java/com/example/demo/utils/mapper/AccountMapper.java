package com.example.demo.utils.mapper;

import com.example.demo.model.Account;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountMapper implements RowMapper<Account> {
    @Override
    public Account mapRow(ResultSet rs) {
        try {
            Account account = new Account();
            account.setId(rs.getLong("id"));
            account.setPassword(rs.getString("password"));
            account.setRole(rs.getString("role"));
            account.setUsername(rs.getString("user_name"));
            account.setEmail(rs.getString("email"));
            return account;
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}
