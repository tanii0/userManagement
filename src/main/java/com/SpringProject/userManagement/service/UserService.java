package com.SpringProject.userManagement.service;

import com.SpringProject.userManagement.dto.LoginDto;
import com.SpringProject.userManagement.entity.User;
import com.SpringProject.userManagement.repository.UserRepository;

import java.util.List;

public interface UserService {
    User createUser(User user);

    User getUserById(Long id);

    List<User> getAllUsers();

    User updateUser(Long id, User user);

    void deleteUser(Long id);

    User getUserWithNotes(Long id);

    String login(LoginDto loginDto);


}
