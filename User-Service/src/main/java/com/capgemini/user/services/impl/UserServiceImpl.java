package com.capgemini.user.services.impl;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.capgemini.user.entities.Hotel;
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
		
		try {
			ResponseEntity<List<Rating>> ratingResponce = ratingService.getRatingsByUserId(id);
			List<Rating> ratingList = ratingResponce.getBody();
			if(ratingResponce.getStatusCode().is2xxSuccessful() && ratingList != null) {
				ratingList.stream()
					.map(rating->{
						try {
							ResponseEntity<Hotel> hotelResponse= hotelService.getHotelById(rating.getHotelId());
							Hotel hotel= hotelResponse.getBody();
							if(hotelResponse.getStatusCode().is2xxSuccessful() && hotel !=null) {
								rating.setHotel(hotel);
							}
						} catch (Exception e) {
							rating.setHotel(null);
						}
						return rating;
					}).collect(Collectors.toList());
				user.setRating(ratingList);
			}
		} catch (Exception e) {
			user.setRating(null);
		}
		
		return user;
	}

	@Override
	public List<User> getAllUsers() {
		List<User> allUser = repository.findAll();
		if(allUser.isEmpty()) throw new ResourceNotFound("No users found");
		
		allUser.stream()
			.map(user->{
				
				String userId = user.getUserId();
				
				try {
					ResponseEntity<List<Rating>> ratingResponce = ratingService.getRatingsByUserId(userId);
					List<Rating> ratingList = ratingResponce.getBody();
					
					
					if(ratingResponce.getStatusCode().is2xxSuccessful() && ratingList != null) {
						ratingList.stream()
							.map(rating->{
								
								try {
									ResponseEntity<Hotel> hotelResponse= hotelService.getHotelById(rating.getHotelId());
									Hotel hotel= hotelResponse.getBody();
									if(hotelResponse.getStatusCode().is2xxSuccessful() && hotel !=null) {
										rating.setHotel(hotel);
									}
									
								} catch (Exception e) {
									rating.setHotel(null);
								}
								return rating;
							}).collect(Collectors.toList());
						user.setRating(ratingList);
					}
				} catch (Exception e) {
					user.setRating(null);
				}
				
				return user;
			}).collect(Collectors.toList());
		
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
	
	public List<Rating> getRatingByUserId(String id){
		ResponseEntity<List<Rating>> ratingResponce = ratingService.getRatingsByUserId(id);
		List<Rating> ratingList = ratingResponce.getBody();
		if(ratingResponce.getStatusCode().is2xxSuccessful() && ratingList != null) {
			return ratingList;
		}
		return null;
	}

}
