package com.example.questappspringproject.services;


import com.example.questappspringproject.entities.User;
import com.example.questappspringproject.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User saveUser(User newUser) {
        return userRepository.save(newUser);
    }

    public User getUser(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    public User updateUser(Long userId, User updateUser) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            User foundUser = user.get();
            foundUser.setUserName(updateUser.getUserName());
            foundUser.setPassword(updateUser.getPassword());
            userRepository.save(foundUser);
            return foundUser;
        }
        else{
            return null;
        }
    }

    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }
}
