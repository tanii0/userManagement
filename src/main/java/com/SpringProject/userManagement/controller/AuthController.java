package com.SpringProject.userManagement.controller;

import com.SpringProject.userManagement.dto.JwtResponse;
import com.SpringProject.userManagement.dto.LoginRequest;
import com.SpringProject.userManagement.service.CustomUserDetailService;
import com.SpringProject.userManagement.util.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.password.PasswordEncoder;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private CustomUserDetailService customUserDetailService;


    @PostMapping("/login")
    public JwtResponse login(@RequestBody LoginRequest loginRequest) {

        UserDetails userDetails =
                customUserDetailService.loadUserByUsername(loginRequest.getEmail());

        System.out.println("================================");
        System.out.println("Entered Email    : " + loginRequest.getEmail());
        System.out.println("Entered Password : " + loginRequest.getPassword());
        System.out.println("Stored Password  : " + userDetails.getPassword());

        boolean match = passwordEncoder.matches(
                loginRequest.getPassword(),
                userDetails.getPassword());

        System.out.println("Password Match = " + match);
        System.out.println("================================");

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );

        String token = jwtUtil.generateToken(userDetails.getUsername());

        return new JwtResponse(token);
    }
}

