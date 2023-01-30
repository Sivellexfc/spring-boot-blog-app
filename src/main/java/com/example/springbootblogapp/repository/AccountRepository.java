package com.example.springbootblogapp.repository;

import com.example.springbootblogapp.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account,Long> {

}
