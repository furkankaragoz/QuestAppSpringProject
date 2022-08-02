package com.example.questappspringproject.controllers;


import com.example.questappspringproject.entities.User;
import com.example.questappspringproject.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }



    @PostMapping
    public User createUser(@RequestBody User newUser) {
        return userService.saveUser(newUser);
    }

    @GetMapping("/{userId}")
    public User getUser(@PathVariable Long userId) {
        // custom exception will add.
        return userService.getUser(userId);
    }

    @PutMapping("/{userId}")
    public User updateUser(@PathVariable Long userId, @RequestBody User updateUser) {
        return userService.updateUser(userId,updateUser);
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
    }

}
