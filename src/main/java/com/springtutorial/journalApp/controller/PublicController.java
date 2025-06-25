package com.springtutorial.journalApp.controller;

import com.springtutorial.journalApp.entity.User;
import com.springtutorial.journalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private UserService userService;
    @GetMapping("/health-check")
    public String healthCheck(){
        return "Okay";
    }

    @PostMapping("/create-user")
    public void createUser(@RequestBody User user){
//        userService.saveEntry(user);
        userService.saveNewUser(user);
    }
}
