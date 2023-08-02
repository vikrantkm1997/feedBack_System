package com.lwcd.user.service.services;

import com.lwcd.user.service.entities.User;

import java.util.List;

public interface UserService {
    //user operations

    User saveUser(User user);

    List<User> getAllUser();

    User getUser(String userId);

    void deleteUser(String userId);

    User updateUser(String userId);
}
