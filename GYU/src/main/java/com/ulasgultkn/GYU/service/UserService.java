package com.ulasgultkn.GYU.service;


import com.ulasgultkn.GYU.entity.User;
import com.ulasgultkn.GYU.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;


    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ResponseEntity<?> createUser(User user){
        HashMap<String,Object> hashMap = new HashMap<>();
        try {
            userRepository.save(user);
            hashMap.put("status",true);
            hashMap.put("message","Created new User");
            hashMap.put("result",user);
            return new ResponseEntity<>(hashMap, HttpStatus.CREATED);
        }catch (Exception ex){
            hashMap.put("status",false);
            hashMap.put("error","Not Created new User");

            return new ResponseEntity<>(hashMap, HttpStatus.BAD_REQUEST);
        }


    }
    public ResponseEntity<?> deleteUser(Long id){
        HashMap<String,Object> hashMap = new HashMap<>();
        try {
            userRepository.deleteById(id);
            hashMap.put("status",true);
            hashMap.put("message","Deleted Ok");
            return new ResponseEntity<>(hashMap,HttpStatus.OK);



        }catch (Exception e){
            hashMap.put("status",false);
            hashMap.put("error","Not delete");
            return new ResponseEntity<>(hashMap,HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<?> getAllUsers(){
        HashMap<String,Object> hashMap = new HashMap<>();
        try {
            List<User> userList = userRepository.findAll();
            hashMap.put("status",true);
            hashMap.put("result",userList);

            return new ResponseEntity<>(hashMap,HttpStatus.OK);

        }catch (Exception e){
            hashMap.put("status",false);
            hashMap.put("error","Not found User or Exception for User");

            return new ResponseEntity<>(hashMap,HttpStatus.BAD_REQUEST);
        }
    }
    public ResponseEntity<?> updateUser (Long id,User userDetails){
        HashMap<String,Object> hashMap = new HashMap<>();
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("UserNot Found"));
        user.setName(userDetails.getName());
        user.setEmail(userDetails.getEmail());
        try {
            userRepository.save(user);
            hashMap.put("status",true);
            hashMap.put("message","Updated user");
            hashMap.put("result",user);
            return new ResponseEntity<>(hashMap,HttpStatus.OK);

        }catch (Exception e){
            hashMap.put("status",false);
            hashMap.put("error","Not Updated user");
            return new ResponseEntity<>(hashMap,HttpStatus.OK);

        }
    }
}
