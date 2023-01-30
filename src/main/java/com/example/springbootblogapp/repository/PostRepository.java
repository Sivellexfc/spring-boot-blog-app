package com.example.springbootblogapp.repository;

import com.example.springbootblogapp.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Long> {

}
