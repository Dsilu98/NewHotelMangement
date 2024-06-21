package com.capgemini.rating.services;

import java.util.List;

import com.capgemini.rating.entities.Rating;

public interface RatingService {
	//create
	Rating createRating(Rating rating);
	//get
	Rating getRatingById(String id);
	List<Rating> getAllRatings();
	List<Rating> getRatingByUserId(String id);
	List<Rating> getRatingByHotelId(String id);
	//update
	Rating updateRating(String id,Rating ratingDetails);
	//delete
	String deleteRating(String id);
}
