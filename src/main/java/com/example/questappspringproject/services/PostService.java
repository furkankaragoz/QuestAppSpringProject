package com.example.questappspringproject.services;

import com.example.questappspringproject.entities.Post;
import com.example.questappspringproject.entities.User;
import com.example.questappspringproject.repository.PostRepository;
import com.example.questappspringproject.repository.UserRepository;
import com.example.questappspringproject.requests.PostCreateRequest;
import com.example.questappspringproject.requests.PostUpdateRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    private PostRepository postRepository;
    private UserService userService;

    public PostService(PostRepository postRepository, UserService userService) {
        this.postRepository = postRepository;
        this.userService = userService;
    }



    public List<Post> getAllPosts(Optional<Long> userId) {
        if(userId.isPresent()) {
            return postRepository.findByUserId(userId.get());
        }
        return postRepository.findAll();
    }

    public Post getPostById(Long postId) {
        return postRepository.findById(postId).orElse(null);
    }

    public Post createPost(PostCreateRequest newPostRequest) {
        User user = userService.getUser(newPostRequest.getUserId());
        if(user == null) {
            return null;
        }
        Post toSave = new Post();
        toSave.setId(newPostRequest.getId());
        toSave.setText(newPostRequest.getText());
        toSave.setTitle(newPostRequest.getTitle());
        toSave.setUser(user);
        return postRepository.save(toSave);
    }

    public Post updatePostById(Long postId, PostUpdateRequest updatePostRequest) {
        Optional<Post> post = postRepository.findById(postId);
        if(post.isPresent()) {
            Post toUpdate = post.get();
            toUpdate.setTitle(updatePostRequest.getTitle());
            toUpdate.setText(updatePostRequest.getText());
            postRepository.save(toUpdate);
            return toUpdate;
        }
        return  null;

    }

    public void deletePostById(Long postId) {
         postRepository.deleteById(postId);
    }
}
