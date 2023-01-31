package com.example.springbootblogapp.controller;

import com.example.springbootblogapp.models.Account;
import com.example.springbootblogapp.models.Post;
import com.example.springbootblogapp.services.AccountService;
import com.example.springbootblogapp.services.PostServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class PostController {

    private final PostServices postServices;
    private final AccountService accountService;

    public PostController(PostServices postServices, AccountService accountService) {
        this.postServices = postServices;
        this.accountService = accountService;
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

    @GetMapping("/posts/new")
    public String createNewPost(Model model){
        Optional<Account> account =  accountService.findByEmail("musluhan01@hotmail.com");
        if(account.isPresent()){
            Post post = new Post();
            post.setAccount(account.get());
            model.addAttribute("post",post);
            return "post_new";
        }
        else return "404";
    }

    @PostMapping("/posts/new")
    public String saveNewPost(@ModelAttribute Post post){
        postServices.save(post);
        return "redirect:/post/" + post.getId();

    }


}
