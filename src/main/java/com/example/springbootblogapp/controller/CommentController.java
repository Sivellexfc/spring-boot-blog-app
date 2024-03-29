package com.example.springbootblogapp.controller;

import com.example.springbootblogapp.models.Account;
import com.example.springbootblogapp.models.Comment;
<<<<<<< HEAD
import com.example.springbootblogapp.models.Post;
=======
>>>>>>> origin/master
import com.example.springbootblogapp.services.AccountService;
import com.example.springbootblogapp.services.CommentService;
import com.example.springbootblogapp.services.PostServices;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Optional;

@Controller
public class CommentController {

    private final PostServices postServices;
    private final CommentService commentService;
    private final AccountService accountService;

    public CommentController(PostServices postServices, CommentService commentService, AccountService accountService) {
        this.postServices = postServices;
        this.commentService = commentService;
        this.accountService = accountService;
    }

    @GetMapping("/posts/{id}/leaveComment")
    @PreAuthorize("isAuthenticated()")
    public String getCommentCreatePage(Model model, @PathVariable Long id, Principal principal){
<<<<<<< HEAD
=======
        Long postId = id;

        System.out.println("postId : " + postId);
        System.out.println("PathVariable id : "+ id);
>>>>>>> origin/master

        Comment comment = new Comment();
        String authUsername = "anonymousUser";

        if (principal != null) {
            authUsername = principal.getName();
        }
        Optional<Account> optionalAccount = accountService.findByEmail(authUsername);
        optionalAccount.ifPresent(comment::setAccount);

<<<<<<< HEAD
        Optional<Post> optionalPost = postServices.getById(id);
        optionalPost.ifPresent(comment::setPost);

=======
        comment.setPostIdTemp(postId);
        comment.setPostIdTemp(id);
>>>>>>> origin/master
        System.out.println("getmapping " + comment.getPostIdTemp());

        model.addAttribute("comment",comment);

        return "create_comment";
    }

    @PostMapping("/posts/createComment")
    @PreAuthorize("isAuthenticated()")
    public String postCommentCreate(@ModelAttribute Comment comment){
        commentService.save(comment);
<<<<<<< HEAD
        return "redirect:/posts/" + comment.getPost().getId();
=======
        System.out.println("postmapping :" + comment.getPostIdTemp());
        return "redirect:/posts/" + comment.getPostIdTemp();
>>>>>>> origin/master
    }


}
