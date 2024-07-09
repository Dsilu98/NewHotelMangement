package com.capgemini.user.services.impl;

import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.capgemini.user.dto.HotelDto;
import com.capgemini.user.dto.RatingDto;
import com.capgemini.user.dto.UserDto;
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
	
	@Autowired
	ModelMapper mapper;
	
	@Override
	public UserDto creteUser(UserDto userDto) {
		User user = new User();
		String arndomId = UUID.randomUUID().toString();
		user.setUserId(arndomId);
		
		user =repository.save(user);
		
		return this.userToUserDto(user);
	}

	@Override
	public UserDto getUserById(String id) {
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
			user.setRating(Collections.EMPTY_LIST);
		}
		
		return this.userToUserDto(user);
	}

	@Override
	public List<UserDto> getAllUsers() {
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
					user.setRating(Collections.EMPTY_LIST);
				}
				
				return user;
			}).collect(Collectors.toList());
		
		return this.userListToDtoList(allUser);
	}

	@Override
	public UserDto updateUser(String id, UserDto userDetails) {
		User existingUser = repository.findById(id).orElseThrow(()->new ResourceNotFound("User not foun with user id : "+id));
		existingUser.setAbout(userDetails.getAbout());
		existingUser.setEmail(userDetails.getEmail());
		existingUser.setName(userDetails.getName());
		
		return this.userToUserDto(existingUser);
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
		return Collections.EMPTY_LIST;
	}
	
	
	
	public HotelDto hotelToHotelDto(Hotel hotel) {
		return mapper.map(hotel, HotelDto.class);
	}
	
	public RatingDto ratingToRatingDto(Rating rating) {
		RatingDto dto = new RatingDto();
		dto = mapper.map(rating, RatingDto.class);
		dto.setHotelDto(mapper.map(rating.getHotel(),HotelDto.class));
		return dto;
	}
	
	public List<RatingDto> ratingListToDtoList(List<Rating> ratings){
		List<RatingDto> ratingDtos=
				ratings.stream()
					.map(ele->ratingToRatingDto(ele))
					.collect(Collectors.toList());
		return ratingDtos;
	}
	
	public  UserDto userToUserDto(User user) {
		UserDto userDto = new UserDto();
		userDto = mapper.map(user, UserDto.class);
		userDto.setRatingDtos(this.ratingListToDtoList(user.getRating()));
		
		return userDto;
	}
	
	public List<UserDto> userListToDtoList(List<User> users){
		List<UserDto> userDtos =
				users.stream()
					.map(ele->this.userToUserDto(ele))
					.collect(Collectors.toList());
		return userDtos;
	}
	
}
