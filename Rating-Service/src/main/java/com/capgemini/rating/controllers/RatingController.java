package com.capgemini.rating.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.rating.entities.Rating;
import com.capgemini.rating.services.impl.RatingServiceImpl;

@RestController
@RequestMapping("/ratings")
public class RatingController {
	@Autowired
	public RatingServiceImpl impl;
	//create
	@PostMapping("/")
	public ResponseEntity<Rating> createHotel(@RequestBody Rating rating){
		Rating newRating = impl.createRating(rating);
		return ResponseEntity.status(HttpStatus.CREATED).body(newRating);
	}
	//read
	@GetMapping("/")
	public ResponseEntity<List<Rating>> getAllHotels(){
		List<Rating> allRatings = impl.getAllRatings();
		return ResponseEntity.status(HttpStatus.FOUND).body(allRatings);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Rating> getRatingById(@PathVariable String id){
		Rating rating = impl.getRatingById(id);
		return ResponseEntity.status(HttpStatus.FOUND).body(rating);
	}
	
	
	@GetMapping("/hotels/{hotelId}")
	public ResponseEntity<List<Rating>> getRatingByHotelId(@PathVariable String hotelId){
		List<Rating> ratings = impl.getRatingByHotelId(hotelId);
		return ResponseEntity.status(HttpStatus.FOUND).body(ratings);
	}
	@GetMapping("/users/{userId}")
	public ResponseEntity<List<Rating>> getRatingByUserId(@PathVariable String userId){
		List<Rating> ratings = impl.getRatingByUserId(userId);
		return ResponseEntity.status(HttpStatus.FOUND).body(ratings);
	}
	
	//update
	@PutMapping("/{id}")
	public ResponseEntity<Rating> updateRating(@PathVariable String id,@RequestBody Rating rating){
		Rating updateRating = impl.updateRating(id, rating);
		return ResponseEntity.status(HttpStatus.OK).body(updateRating);
	}
	//delete
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteRating(@PathVariable String id){
		String message = impl.deleteRating(id);
		return ResponseEntity.status(HttpStatus.OK).body(message);
		
	}
}
