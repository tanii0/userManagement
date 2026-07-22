package com.SpringProject.userManagement.controller;

//public class HomeController {

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

    @RestController
    public class HomeController {

        @GetMapping("/")
        public String home() {
            return "User Management Application is successfully deployed on AWS EC2.";
        }
    }

