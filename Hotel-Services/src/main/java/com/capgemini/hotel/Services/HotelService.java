package com.capgemini.hotel.Services;

import java.util.List;

import com.capgemini.hotel.entities.Hotel;

public interface HotelService {
	//create
	Hotel createHotel(Hotel hotel);
	//update
	Hotel updateHotel(String id,Hotel hotelDetails);
	//delete
	String deletHotel(String id);
	//get one hotel
	Hotel getHotelById(String id);
	//get all hotel
	List<Hotel> getAllHotels();
}
