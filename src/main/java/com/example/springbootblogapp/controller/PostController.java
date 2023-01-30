package com.example.springbootblogapp.controller;

import com.example.springbootblogapp.models.Post;
import com.example.springbootblogapp.services.PostServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller
public class PostController {

    private final PostServices postServices;

    public PostController(PostServices postServices) {
        this.postServices = postServices;
    }

    @GetMapping("/post/{id}")
    public String getPost(@PathVariable Long id, Model model){
        Optional<Post> opitonalPost = postServices.getById(id);
        if(opitonalPost.isPresent()){
            Post post = opitonalPost.get();
            model.addAttribute("post",post);
            return "post";
        }
        else return "404";
    }


}
