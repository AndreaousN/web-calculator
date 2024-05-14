package com.spring.calculator.service;

import com.spring.calculator.model.Number;
import com.spring.calculator.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    void createUser(User user);

    User getUserById(int id);

    void updateUser(User user);

    void deleteUserById(int id);

    User getUserByEmail(String email);

    User getUserByUsername(String username);

    boolean authenticateUser(String username, String password);
}
