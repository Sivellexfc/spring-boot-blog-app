package com.example.springbootblogapp.repository;

import com.example.springbootblogapp.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
