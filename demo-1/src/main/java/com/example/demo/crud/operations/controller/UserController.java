package com.example.demo.crud.operations.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.crud.operations.entity.User;
import com.example.demo.crud.operations.exception.ResourceNotFoundException;
import com.example.demo.crud.operations.repository.UserRepository;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;
    
    //get all users
    @GetMapping(path = "/all")
    public List<User> getAllUsers(){
    	return this.userRepository.findAll();
    }
    
    //get user by id
    @GetMapping(path = "/{id}")
    public User getUserById(@PathVariable(value = "id") long id) {
    	return this.userRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("User not found with id:" + id));
    }
    
    //create user
    @PostMapping(path = "/create")
    public User createUser(@RequestBody User user) {
    	return this.userRepository.save(user);
    }
    
    //update user
    @PutMapping(path = "/update")
    public User updateUser(@RequestParam long id,@RequestBody User user) {
    	User exists= this.userRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("User not found with id:" + id));
       exists.setFirstname("cccc");
       return this.userRepository.save(exists);
       
    }
    
    //delete user
    @DeleteMapping(path = "/delete")
    public ResponseEntity<User> deleteUser(@RequestParam long id) {
    	User exists= this.userRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("User not found with id:" + id));
    	this.userRepository.delete(exists);
    	return ResponseEntity.ok().build();
    }
}
