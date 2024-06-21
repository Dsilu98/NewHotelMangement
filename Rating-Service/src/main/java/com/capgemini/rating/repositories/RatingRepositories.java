package com.capgemini.rating.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capgemini.rating.entities.Rating;

public interface RatingRepositories extends JpaRepository<Rating, String> {
	List<Rating> findByuserId(String userId);
	List<Rating> findByhotelId(String hotelId);
}
