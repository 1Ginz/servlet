package com.example.demo.service.impl;

import com.example.demo.dao.IAccountDao;
import com.example.demo.dao.impl.AccountDao;
import com.example.demo.model.Account;
import com.example.demo.service.IAccountService;

public class AccountService implements IAccountService {

    private IAccountDao accountDao;

    public AccountService(){
        this.accountDao = new AccountDao();
    }

    @Override
    public Account findOne(Long id) {
         return accountDao.getById(id);
    }

    @Override
    public Account findByEmail(String email,String password) {
        return accountDao.getByEmail(email,password);
    }

    @Override
    public Account Create(Account account) {
        Long id = accountDao.save(account);
        account.setId(id);
        return account;
    }
}
