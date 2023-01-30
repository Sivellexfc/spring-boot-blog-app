package com.example.springbootblogapp.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String email;
    private String password;

    private String username;

    private String firstName;
    private String lastName;

    @OneToMany(mappedBy = "account")
    private List<Post> posts = new ArrayList<>();

    public void addPost(Post post){
        posts.add(post);
    }

}
