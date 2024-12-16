package com.ulasgultkn.GYU.controller;

import com.ulasgultkn.GYU.entity.User;
import com.ulasgultkn.GYU.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody User user){
        return userService.createUser(user);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@RequestBody User user,@PathVariable Long id){
        return userService.updateUser(id,user);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id){
        return userService.deleteUser(id);
    }
    @GetMapping
    public ResponseEntity<?> getAllUsers(){
        return userService.getAllUsers();
    }


}
