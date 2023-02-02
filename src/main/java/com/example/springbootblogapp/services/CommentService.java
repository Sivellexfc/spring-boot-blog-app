package com.example.springbootblogapp.services;

import com.example.springbootblogapp.models.Comment;
import com.example.springbootblogapp.repository.CommentRepository;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public Comment save(Comment comment){
        return commentRepository.save(comment);
    }

    public void delete(Comment comment){
        commentRepository.delete(comment);
    }

}
