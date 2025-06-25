package com.springtutorial.journalApp.controller;

import com.springtutorial.journalApp.entity.JournalEntry;
import com.springtutorial.journalApp.entity.User;
import com.springtutorial.journalApp.repository.UserRepository;
import com.springtutorial.journalApp.service.JournalEntryService;
import com.springtutorial.journalApp.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<User> getAllUsers(){
        return  userService.getAll();
    }



//    @DeleteMapping
//    public void deleteUser(@PathVariable ObjectId myUserId){
//        userService.deleteById(myUserId);
//    }

    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User user){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        User userInfo = userService.findByUserName(username);
        if(userInfo != null){
            userInfo.setUsername(user.getUsername());
            userInfo.setPassword(user.getPassword());
            userService.saveNewUser(userInfo);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/user")
    public ResponseEntity<?> deleteUserById(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        userRepository.deleteByUsername(authentication.getName());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
