package com.example.bank.controller;

import com.example.bank.model.User;
import com.example.bank.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Controller

public class BankController {

    @Autowired
    UserRepo userRepo;

    @GetMapping(path = "/users")
    public ResponseEntity<Object> getUsers(){
       List<User> users = userRepo.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping(path = "/users", consumes = "application/json")
    public ResponseEntity<Object> addUser(@RequestBody User user){

        User userAdded = userRepo.save(user);
        if (userAdded.getUserId().equals(null)){
            return new ResponseEntity<>(user, HttpStatus.PROCESSING);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
