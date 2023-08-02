package com.lwcd.user.service.services.impl;

import com.lwcd.user.service.entities.User;
import com.lwcd.user.service.repositories.UserRepository;
import com.lwcd.user.service.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lwcd.user.service.exceptions.ResourceNotFoundException;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Override
    public User saveUser(User user) {
        //This will generate unique userId
        String randomUserId = UUID.randomUUID().toString();
        user.setUserId(randomUserId);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {

       return userRepository.findAll();
    }

    @Override
    public User getUser(String userId) {
        return userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("UserDoes Not exist" + userId));
    }

    @Override
    public void deleteUser(String userId) {
        userRepository.deleteById(userId);
        return;
    }

    @Override
    public User updateUser(String userId) {
        User user = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("UserDoes Not exist!!!" + userId));
        return user;
    }
}
