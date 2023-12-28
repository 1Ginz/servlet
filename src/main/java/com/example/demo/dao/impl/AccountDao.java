package com.example.demo.dao.impl;

import com.example.demo.dao.IAccountDao;
import com.example.demo.model.Account;
import com.example.demo.utils.mapper.AccountMapper;

import java.util.List;

public class AccountDao extends AbstractDao<Account> implements IAccountDao {

    public AccountDao() {
    }

    @Override
    public Long save(Account account){
        StringBuilder sql = new StringBuilder("INSERT INTO account ");
        sql.append("(password, role, email, user_name) ");
        sql.append("VALUES('");
        sql.append(account.getPassword());sql.append("', '");
        sql.append(account.getRole());sql.append("', '");
        sql.append(account.getEmail());sql.append("', '");
        sql.append(account.getUsername());sql.append("')");
        return insert(sql.toString());
    }

    @Override
    public Account getById(Long id) {
        String sql = "Select * from account where id = ?";
        List<Account> accounts = query(sql,new AccountMapper(),id);
        return accounts == null ? null : accounts.get(0);
    }

    @Override
    public Account getByEmail(String email,String password) {
        StringBuilder sql = new StringBuilder("SELECT * FROM account");
        sql.append(" WHERE email = '"+email+"' AND password = '" + password + "'");
        System.out.println(sql);
        List<Account> accounts = query(sql.toString(),new AccountMapper());
        return accounts == null ? null : accounts.get(0);
    }


}
