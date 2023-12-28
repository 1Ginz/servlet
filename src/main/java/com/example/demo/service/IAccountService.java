package com.example.demo.service;

import com.example.demo.model.Account;

public interface IAccountService {

    Account findOne(Long id);

    Account findByEmail(String email,String password);

    Account Create(Account account);

}
