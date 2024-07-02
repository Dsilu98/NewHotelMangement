package com.capgemini.hotel.Services.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.hotel.Services.HotelService;
import com.capgemini.hotel.entities.Hotel;
import com.capgemini.hotel.exceptions.ResourceNotFound;
import com.capgemini.hotel.repositories.HotelRepository;

@Service
public class HotelServiceImpl implements HotelService{
	private HotelRepository repository;
	
	public HotelServiceImpl(HotelRepository hotelRepository) {
		this.repository=hotelRepository;
	}

	@Override
	public Hotel createHotel(Hotel hotel) {
		String randomId = UUID.randomUUID().toString();
		hotel.setId(randomId);
		return repository.save(hotel);
	}

	@Override
	public Hotel updateHotel(String id, Hotel hotelDetails) {
		Hotel existingHotel = repository.findById(id).orElseThrow(()->new ResourceNotFound("Hotel not found with hotel id : "+id));
		existingHotel.setHotelName(hotelDetails.getHotelName());
		existingHotel.setAbout(hotelDetails.getAbout());
		existingHotel.setLocation(hotelDetails.getLocation());
		
		return repository.save(existingHotel);
	}

	@Override
	public String deletHotel(String id) {
		repository.findById(id).orElseThrow(()->new ResourceNotFound("No hotel data found for the id : "+id));
		repository.deleteById(id);
		return "Hotel Data deleted with id : "+id;
	}

	@Override
	public Hotel getHotelById(String id) {
		Hotel hotel = repository.findById(id).orElseThrow(()->new ResourceNotFound("Hotel not found with hotel id : "+id));
		return hotel;
	}

	@Override
	public List<Hotel> getAllHotels() {
		List<Hotel> allHotels = repository.findAll();
		if(allHotels.isEmpty()) throw new ResourceNotFound("Not Hotel Data Present");
		
		return allHotels;
	}

}
