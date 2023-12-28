package com.example.demo.dao;

import com.example.demo.model.Account;

public interface IAccountDao extends IAbstractDao<Account> {

    Long save(Account account);

    Account getById(Long id);

    Account getByEmail(String email,String password);
}
