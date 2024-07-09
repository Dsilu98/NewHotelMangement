package com.capgemini.user.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.user.dto.UserDto;
import com.capgemini.user.entities.Rating;
import com.capgemini.user.entities.User;
import com.capgemini.user.services.impl.UserServiceImpl;

import jakarta.ws.rs.Path;

@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
	private UserServiceImpl serviceImpl;
	
	//create
	@PostMapping("/")
	public ResponseEntity<UserDto> createUser(@RequestBody UserDto user){
		UserDto creteUser = serviceImpl.creteUser(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(creteUser);
	}
	//read
	@GetMapping("/{id}")
	public ResponseEntity<UserDto> getuserById(@PathVariable String id){
		UserDto userById = serviceImpl.getUserById(id);
		return ResponseEntity.status(HttpStatus.FOUND).body(userById);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<UserDto>> getUsers(){
		 List<UserDto> allUsers = serviceImpl.getAllUsers();
		return ResponseEntity.status(HttpStatus.FOUND).body(allUsers);
	}
	//update
	@PutMapping("/{id}")
	public ResponseEntity<UserDto> updateUser(@PathVariable String id,@RequestBody UserDto user){
		UserDto updateUser = serviceImpl.updateUser(id, user);
		return ResponseEntity.status(HttpStatus.OK).body(updateUser);
	}
	
	//delete
	@GetMapping("/ratings/users/{id}")
	public ResponseEntity<List<Rating>> getRatingsByUserId(@PathVariable String id){
		List<Rating> ratingByUserId = serviceImpl.getRatingByUserId(id);
		return new ResponseEntity<>(ratingByUserId,HttpStatus.OK);
	}
}
