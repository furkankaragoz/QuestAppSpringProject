package com.example.questappspringproject.controllers;


import com.example.questappspringproject.entities.Post;
import com.example.questappspringproject.requests.PostCreateRequest;
import com.example.questappspringproject.requests.PostUpdateRequest;
import com.example.questappspringproject.services.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/posts")
public class PostController {

    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public List<Post> getAllPosts(@RequestParam Optional<Long> userId) {
         return postService.getAllPosts(userId);
    }

    @GetMapping("/{postId}")
    public Post getPost(@PathVariable Long postId) {
        return postService.getPostById(postId);
    }

    @PostMapping
    public Post createPost(@RequestBody PostCreateRequest newPostRequest){
        return  postService.createPost(newPostRequest);
    }

    @PutMapping("/{postId}")
    public Post updatePost(@PathVariable Long postId, @RequestBody PostUpdateRequest updatePostRequest) {
        return postService.updatePostById(postId,updatePostRequest);
    }

    @DeleteMapping("/{postId}")
    public void deletePost(@PathVariable Long postId) {
         postService.deletePostById(postId);
    }
}
