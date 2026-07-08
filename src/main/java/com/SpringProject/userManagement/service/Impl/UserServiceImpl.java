package com.SpringProject.userManagement.service.Impl;

import com.SpringProject.userManagement.dto.LoginDto;
import com.SpringProject.userManagement.dto.Userdto;
import com.SpringProject.userManagement.entity.User;
import com.SpringProject.userManagement.repository.UserRepository;
import com.SpringProject.userManagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import com.SpringProject.userManagement.exception.ResourceNotFoundException;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

 //   @Override
  //  public User createUser(User user) {
        // return null;
      //  String encryptedPassword = passwordEncoder.encode(user.getPassword());
       // user.setPassword(encryptedPassword);
       // return userRepository.save(user);
       // @Override
       // public User createUser(User user) {

          //  user.setPassword(passwordEncoder.encode(user.getPassword()));

          //  return userRepository.save(user);
      //  }
    @Override
    public User createUser(User user) {

        System.out.println("Before encoding: " + user.getPassword());

        String encodedPassword = passwordEncoder.encode(user.getPassword());

        System.out.println("Encoded password: " + encodedPassword);

        user.setPassword(encodedPassword);

        return userRepository.save(user);
    }
  //  }

    @Override
    public User getUserById(Long id) {

        return null;
    }

    @Override
    public List<User> getAllUsers() {
        return null;
    }

   // @Override
    //public User updateUser(Long id, User user) {
      //  return null;
   // }
   @Override
   public User updateUser(Long id, User user) {

       // Find the existing user from the database
       User existingUser = userRepository.findById(id)
               .orElseThrow(() ->
                       new ResourceNotFoundException("User not found"));

       // Update the user's name
       existingUser.setName(user.getName());

       // Update the user's email
       existingUser.setEmail(user.getEmail());

       // Encode the new password and save it
       existingUser.setPassword(passwordEncoder.encode(user.getPassword()));

       // Save the updated user
       return userRepository.save(existingUser);
   }

    @Override
    public void deleteUser(Long id) {

    }

    @Override
    public User getUserWithNotes(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }

    /*  private User dtoToEntity(Userdto userdto){
          User user = new User();
          user.setName(userdto.getName());
          user.setEmail(userdto.getEmail());
          user.setPassword(userdto.getPassword());
          return user;
      }*/
    @Autowired
    //private BCryptPasswordEncoder passwordEncoder;
    private PasswordEncoder passwordEncoder;

    @Override
    public String login(LoginDto loginDto) {
        User user = userRepository.findByEmail(loginDto.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with email: " + loginDto.getEmail()));
        boolean passwordMatched = passwordEncoder.matches(loginDto.getPassword(), user.getPassword());
        if (passwordMatched) {
            return "Login successful";
        }
        return "Invalid Password";
    }
}
