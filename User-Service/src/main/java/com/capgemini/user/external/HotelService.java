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

import com.capgemini.user.entities.Hotel;
import com.capgemini.user.entities.Rating;

@Service
@FeignClient(name = "HOTEL-SERVICES")
public interface HotelService {
	//create
	@PostMapping("/hotels/")
	public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel);
	//read
	@GetMapping("/hotels/")
	public ResponseEntity<List<Hotel>> getHotel();
	
	@GetMapping("/hotels/{id}")
	public ResponseEntity<Hotel> getHotelById(@PathVariable String id);
	
	//update
	@PutMapping("/hotels/{id}")
	public ResponseEntity<Hotel> updateHotel(@PathVariable String id,@RequestBody Hotel hotel);
	//delete
	@DeleteMapping("/hotels/{id}")
	public ResponseEntity<String> deleteHotel(@PathVariable String id);
}
