package com.lwcd.user.service.controllers;

import com.lwcd.user.service.entities.User;
import com.lwcd.user.service.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
     //create

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<User> createUser (@RequestBody User user)
    {
        User user1 = userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user1);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUser(@PathVariable String userId)
    {
        User user = userService.getUser(userId);
        return ResponseEntity.ok(user); //similar to ResponseEntity.status(HttpStatus.OK).body(user1);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUser()
    {
        List<User> userList = userService.getAllUser();
        return ResponseEntity.ok(userList);
    }
}
