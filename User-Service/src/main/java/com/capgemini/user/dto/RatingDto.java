package com.capgemini.user.dto;

import com.capgemini.user.entities.Hotel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RatingDto {
    private  int rating;
    private  String feedback;
    
    private HotelDto hotelDto;
}
