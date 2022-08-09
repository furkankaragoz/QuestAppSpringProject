package com.example.questappspringproject.services;

import com.example.questappspringproject.entities.Comment;
import com.example.questappspringproject.entities.Post;
import com.example.questappspringproject.entities.User;
import com.example.questappspringproject.repository.CommentRepository;
import com.example.questappspringproject.requests.CommentCreateRequest;
import com.example.questappspringproject.requests.CommentUpdateRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    private CommentRepository commentRepository;
    private UserService userService;
    private PostService postService;

    public CommentService(CommentRepository commentRepository, UserService userService, PostService postService) {
        this.commentRepository = commentRepository;
        this.userService = userService;
        this.postService = postService;
    }

    public List<Comment> getAllCommentWithParam(Optional<Long> userId, Optional<Long> postId) {
        if(userId.isPresent() & postId.isPresent()) {
            return commentRepository.findByUserIdAndPostId(userId.get(), postId.get());
        }
        else if(userId.isPresent()) {
            return commentRepository.findByUserId(userId.get());
        }
        else if(postId.isPresent()){
            return  commentRepository.findByPostId(postId.get());
        }
        else
            return commentRepository.findAll();
    }


    public Comment getComment(Long commentId) {
        return commentRepository.findById(commentId).orElse(null);
    }

    public Comment createComment(CommentCreateRequest newCommentRequest) {
        User user = userService.getUser(newCommentRequest.getUserId());
        Post post = postService.getPostById(newCommentRequest.getPostId());

        if(user != null && post != null) {

            Comment comment = new Comment();
            comment.setId(newCommentRequest.getId());
            comment.setText(newCommentRequest.getText());
            comment.setPost(post);
            comment.setUser(user);
            return commentRepository.save(comment);
        } else
             return null;
    }

    public Comment updateComment(Long commentId, CommentUpdateRequest commentUpdateRequest) {
        Optional<Comment> comment = commentRepository.findById(commentId);
        if(comment.isPresent()) {
            Comment commentToUpdate = comment.get();
            commentToUpdate.setText(commentUpdateRequest.getText());
            return commentRepository.save(commentToUpdate);
        }
        else
            return null;
    }

    public void deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }
}
