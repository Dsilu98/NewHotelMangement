package com.capgemini.rating.services.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.rating.entities.Rating;
import com.capgemini.rating.exceptions.ResourceNotFound;
import com.capgemini.rating.repositories.RatingRepositories;
import com.capgemini.rating.services.RatingService;

@Service
public class RatingServiceImpl implements RatingService{
	@Autowired
	private RatingRepositories repositories;

	@Override
	public Rating createRating(Rating rating) {
		String randomId = UUID.randomUUID().toString();
		rating.setRatingId(randomId);
		return repositories.save(rating);
	}

	@Override
	public Rating getRatingById(String id) {
		Rating rating = repositories.findById(id).orElseThrow(()->new ResourceNotFound("Rating not gound with the rating id : "+id));
		return rating;
	}

	@Override
	public List<Rating> getAllRatings() {
		List<Rating> allRatings = repositories.findAll();
		return allRatings;
		
	}

	@Override
	public Rating updateRating(String id, Rating ratingDetails) {
		Rating existingRating = repositories.findById(id).orElseThrow(()->new ResourceNotFound("No such hotel found with id : "+id));
		existingRating.setFeedback(ratingDetails.getFeedback());
		existingRating.setRating(ratingDetails.getRating());
		return existingRating;
	}

	@Override
	public String deleteRating(String id) {
		Rating existingRating = repositories.findById(id).orElseThrow(()->new ResourceNotFound("No such hotel found with id : "+id));
		repositories.deleteById(id);
		return "Hotel deleted by id : "+id;
	}

	@Override
	public List<Rating> getRatingByUserId(String id) {
		List<Rating> ratingLists = repositories.findByuserId(id);
		if(ratingLists.isEmpty()) throw new ResourceNotFound("No Ratins fond!!");
		return ratingLists;
	}

	@Override
	public List<Rating> getRatingByHotelId(String id) {
		List<Rating> allRatings = repositories.findByhotelId(id);
		if(allRatings.isEmpty()) throw new ResourceNotFound("No Ratings Found!!");
		return allRatings;
	}
	
	
}
