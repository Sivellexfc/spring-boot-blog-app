package com.example.springbootblogapp.controller;

import com.example.springbootblogapp.models.Post;
import com.example.springbootblogapp.services.PostServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    private final PostServices postServices;

    public HomeController(PostServices postServices) {
        this.postServices = postServices;
    }

    @GetMapping(value = "/")
    public String home(Model model){
        List<Post> posts = postServices.getAll();
        model.addAttribute("posts" , posts);
        return "home";
    }
}
