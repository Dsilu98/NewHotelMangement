package com.capgemini.user.dto;

import java.util.List;

import com.capgemini.user.entities.Rating;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
	
	private String name;
	
	private String email;
	
	private String about;
	
	
	private List<RatingDto> ratingDtos;
}
