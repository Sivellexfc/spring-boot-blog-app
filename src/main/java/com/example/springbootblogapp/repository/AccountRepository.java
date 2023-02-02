package com.example.springbootblogapp.repository;

import com.example.springbootblogapp.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account,Long> {

    Optional<Account> findOneByEmail(String email);
}
