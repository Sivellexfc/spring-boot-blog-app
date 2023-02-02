package com.example.springbootblogapp.controller;

import com.example.springbootblogapp.models.Account;
import com.example.springbootblogapp.models.Authority;
import com.example.springbootblogapp.models.Comment;
import com.example.springbootblogapp.models.Post;
import com.example.springbootblogapp.services.AccountService;
import com.example.springbootblogapp.services.PostServices;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Controller
public class PostController {

    private final PostServices postServices;
    private final AccountService accountService;

    public PostController(PostServices postServices, AccountService accountService) {
        this.postServices = postServices;
        this.accountService = accountService;
    }

    @GetMapping("/posts/{id}")
    public String getPost(@PathVariable Long id, Model model){
        Optional<Post> opitonalPost = postServices.getById(id);
        if(opitonalPost.isPresent()){
            Post post = opitonalPost.get();
            List<Comment> comments = post.getComments();
            post.setViewCount(post.getViewCount() + 1);
            postServices.save(post);
            model.addAttribute("comments",comments);
            model.addAttribute("post",post);
            return "post";
        }
        else return "404";
    }

    @PostMapping("/posts/{id}")
    @PreAuthorize("isAuthenticated()")
    public String updatePost(@PathVariable Long id, Post post, BindingResult result, Model model, Principal principal) {
        String authUsername = "anonymousUser";
        if (principal != null) {
            authUsername = principal.getName();
        }
        Optional<Account> optionalAccount = accountService.findByEmail(authUsername);
        Optional<Post> optionalPost = postServices.getById(id);

        if (optionalPost.isPresent() && optionalPost.get().getAccount() == optionalAccount.get()) {
            Post existingPost = optionalPost.get();

            existingPost.setTitle(post.getTitle());
            existingPost.setBody(post.getBody());
            existingPost.setUpdatedAt(LocalDateTime.now());

            postServices.save(existingPost);
            return "redirect:/posts/" + post.getId();
        }
        return "404";

    }

    @GetMapping("/posts/new")
    @PreAuthorize("isAuthenticated()")
    public String createNewPost(Model model, Principal principal){

        String authUsername = "anonymousUser";
        if (principal != null) {
            authUsername = principal.getName();
        }

        Optional<Account> account =  accountService.findByEmail(authUsername);
        if(account.isPresent()){
            Post post = new Post();
            post.setAccount(account.get());
            model.addAttribute("post",post);
            return "post_new";
        }
        else return "404";
    }

    @PostMapping("/posts/new")
    @PreAuthorize("isAuthenticated()")
    public String createNewPost(@ModelAttribute Post post, Principal principal) {

        String authUsername = "anonymousUser";
        if (principal != null) {
            authUsername = principal.getName();
        }
        if (post.getAccount().getEmail().compareToIgnoreCase(authUsername) < 0) {
            // TODO:
        }
        postServices.save(post);
        return "redirect:/posts/" + post.getId();
    }

    @GetMapping("/posts/{id}/delete")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String deletePost(@PathVariable Long id) {

        System.out.println("delete section");
        // find post by id
        Optional<Post> optionalPost = postServices.getById(id);
        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();
            postServices.delete(post);
            return "redirect:/";
        } else {
            return "404";
        }
    }

    @GetMapping("/posts/{id}/edit")
    @PreAuthorize("isAuthenticated()")
    public String getPostForEdit(@PathVariable Long id, Model model){
        Optional<Post> optionalPost = postServices.getById(id);

        if(optionalPost.isPresent()){
            Post post = optionalPost.get();
            model.addAttribute("post",post);
            return "post_edit";
        }
        else return "404";

    }





}
