package com.lwcd.user.service.controllers;

import com.lwcd.user.service.entities.User;
import com.lwcd.user.service.services.UserService;
import com.lwcd.user.service.services.impl.UserServiceImpl;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
     //create

    @Autowired
    private UserService userService;

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);


    @PostMapping
    public ResponseEntity<User> createUser (@RequestBody User user)
    {
        User user1 = userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user1);
    }

    int retryCount = 1;
    @GetMapping("/{userId}")
//    @CircuitBreaker(name="ratingHotelBreaker",fallbackMethod = "ratingHotelFallback")
    @Retry(name = "ratingHotelService", fallbackMethod = "ratingHotelFallback")
    public ResponseEntity<User> getUser(@PathVariable String userId)
    {
        User user = userService.getUser(userId);
        System.out.println("Retry Count right now is " + retryCount);
        retryCount++;
        return ResponseEntity.ok(user); //similar to ResponseEntity.status(HttpStatus.OK).body(user1);
    }

    public ResponseEntity<User> ratingHotelFallback(String userId,Exception ex)
    {
        logger.info("Fallback is called as some service is down " + ex.getMessage());
        User user = User.builder()
                        .email("dummyEmail@email.com")
                        .name("Dummy name")
                        .about("User is created as dummy")
                        .userId("124121").build();
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUser()
    {
        List<User> userList = userService.getAllUser();
        return ResponseEntity.ok(userList);
    }
}
