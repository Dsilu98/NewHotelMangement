package com.capgemini.user.services.impl;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.capgemini.user.entities.Rating;
import com.capgemini.user.entities.User;
import com.capgemini.user.exceptions.ResourceNotFound;
import com.capgemini.user.external.HotelService;
import com.capgemini.user.external.RatingService;
import com.capgemini.user.repositories.UserRepository;
import com.capgemini.user.services.UserServices;

@Service
public class UserServiceImpl implements UserServices{
	@Autowired
	private UserRepository repository;
	@Autowired
	private RatingService ratingService;
	@Autowired 
	private HotelService hotelService;
	
	@Override
	public User creteUser(User user) {
		String arndomId = UUID.randomUUID().toString();
		user.setUserId(arndomId);
		
		return repository.save(user);
	}

	@Override
	public User getUserById(String id) {
		User user = repository.findById(id).orElseThrow(()->new ResourceNotFound("User not foun with user id : "+id));
		return user;
	}

	@Override
	public List<User> getAllUsers() {
		List<User> allUser = repository.findAll();
		if(allUser.isEmpty()) throw new ResourceNotFound("No users found");
		
		return allUser;
	}

	@Override
	public User updateUser(String id, User userDetails) {
		User existingUser = repository.findById(id).orElseThrow(()->new ResourceNotFound("User not foun with user id : "+id));
		existingUser.setAbout(userDetails.getAbout());
		existingUser.setEmail(userDetails.getEmail());
		existingUser.setName(userDetails.getName());
		
		return existingUser;
	}

	@Override
	public void deleteUser(String id) {
		repository.findById(id).orElseThrow(()->new ResourceNotFound("User not foun with user id : "+id));
		repository.deleteById(id);
	}
	
	public List<Rating> getAllRatings(){
		return  ratingService.getAllRatings().getBody();
	}

}
