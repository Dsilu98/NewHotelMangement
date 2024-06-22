package com.capgemini.user.external;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.capgemini.user.entities.Rating;

@Service
@FeignClient(name = "RATING-SERVICE")
public interface RatingService {
	//create
	@PostMapping("/ratings/")
	public ResponseEntity<Rating> createRating(@RequestBody Rating rating);
	
	//read
	@GetMapping("/ratings/{id}")
	public ResponseEntity<Rating> getRatingById(@PathVariable String id);
	
	@GetMapping("ratings/")
	public ResponseEntity<List<Rating>> getAllRatings();
	
	@GetMapping("/ratings/users/{id}")
	public ResponseEntity<List< Rating>> getRatingsByUserId(@PathVariable String id);
	
	@GetMapping("/ratings/hotels/{id}")
	public ResponseEntity<List<Rating>> getRaingByHotelId(@PathVariable String id);
	//update
	@PutMapping("/ratings/{id}")
	public ResponseEntity<Rating> updateRatingByRatingId(@PathVariable String id,@RequestBody Rating rating);
	//delete
	@DeleteMapping("/ratings/{id}")
	public ResponseEntity<String> deletRatingById(@PathVariable String id);
}
