package com.example.springbootblogapp.repository;

import com.example.springbootblogapp.models.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority,String> {
}
