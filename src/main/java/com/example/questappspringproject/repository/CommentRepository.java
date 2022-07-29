package com.example.questappspringproject.repository;

import com.example.questappspringproject.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,Long> {
}
