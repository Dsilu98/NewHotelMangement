package com.capgemini.hotel.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.hotel.Services.impl.HotelServiceImpl;
import com.capgemini.hotel.entities.Hotel;

import jakarta.ws.rs.Path;

@RestController
@RequestMapping("/hotels")
public class HotelController {
	@Autowired
	HotelServiceImpl impl;
	
	//create
	@PostMapping("/")
	public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel){
		Hotel newHotel = impl.createHotel(hotel);
		return ResponseEntity.status(HttpStatus.CREATED).body(newHotel);
	}
	//get
	@GetMapping("/{id}")
	public ResponseEntity<Hotel> getHotelById(@PathVariable String id){
		Hotel hotel = impl.getHotelById(id);
		return ResponseEntity.status(HttpStatus.FOUND).body(hotel);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<Hotel>> getAllHotels(){
		List<Hotel> allHotels = impl.getAllHotels();
		return ResponseEntity.status(HttpStatus.FOUND).body(allHotels);
	}
	//update
	@PostMapping("/{id}")
	public ResponseEntity<Hotel> updateHotel(@PathVariable String id,@RequestBody Hotel hotel){
		Hotel updatedHotel = impl.updateHotel(id, hotel);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(updatedHotel);
	}
	//delete
	@DeleteMapping("/{}")
	public ResponseEntity<String> deleteHotelById(@PathVariable String id){
		String message = impl.deletHotel(id);
		return ResponseEntity.status(HttpStatus.OK).body(message);
	}
}
