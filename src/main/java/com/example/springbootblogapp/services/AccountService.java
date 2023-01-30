package com.example.springbootblogapp.services;

import com.example.springbootblogapp.models.Account;
import com.example.springbootblogapp.repository.AccountRepository;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account save(Account account){
        return accountRepository.save(account);
    }
}
