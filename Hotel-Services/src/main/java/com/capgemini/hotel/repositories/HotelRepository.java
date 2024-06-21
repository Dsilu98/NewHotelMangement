package com.capgemini.hotel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capgemini.hotel.entities.Hotel;

public interface HotelRepository extends JpaRepository<Hotel, String> {

}
