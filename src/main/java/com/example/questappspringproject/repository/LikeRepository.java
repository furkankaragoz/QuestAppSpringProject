package com.example.questappspringproject.repository;

import com.example.questappspringproject.entities.Like;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository <Like,Long> {
}
