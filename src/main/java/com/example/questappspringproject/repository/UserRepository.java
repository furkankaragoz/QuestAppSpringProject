package com.example.questappspringproject.repository;

import com.example.questappspringproject.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {

}
