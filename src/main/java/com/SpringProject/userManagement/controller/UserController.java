package com.SpringProject.userManagement.controller;
import com.SpringProject.userManagement.entity.User;
import com.SpringProject.userManagement.repository.UserRepository;
import com.SpringProject.userManagement.service.UserService;
import jakarta.persistence.Column;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.security.crypto.password.PasswordEncoder;



import com.SpringProject.userManagement.dto.Userdto;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.awt.*;
@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("user")


public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;



    //localhost8080/users
    @GetMapping("/users")
    public List<User> getAllUsers() {
       List<User> users = userService.getAllUsers();

        return users;
    }
    @GetMapping("/users/{id}")
    //localhost:8080/users/1
    public User getUser(@PathVariable int id){
        User user = userService.getUserById((long) id);
        return user;

    }
   @PostMapping("/user/add")

    public User createUser(@RequestBody Userdto userdto) {
        User user = new User();
        user.setName(userdto.getName());
        user.setEmail(userdto.getEmail());
       user.setPassword(userdto.getPassword());


        return userService.createUser(user);

    }


    @PutMapping("/user/update/{id}")
    public User updateUser(@PathVariable int id,  @RequestBody Userdto userdto) {

        User user = userService.getUserById((long) id);

        user.setName(userdto.getName());
        user.setEmail(userdto.getEmail());
       // user.setPassword(passwordEncoder.encode(userdto.getPassword()));
        user.setPassword(userdto.getPassword());

        userService.updateUser((long) id, user);

        return user;
    }
   @DeleteMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable int id) {

        userService.deleteUser((long) id);

        return  "User deleted successfully";
    }
    @GetMapping("/{id}/notes")
    public User getUserWithNotes(@PathVariable Long id) {
        return userService.getUserWithNotes(id);

    }
    @Column(name = "users_email", unique = true)
    private String email;

}
