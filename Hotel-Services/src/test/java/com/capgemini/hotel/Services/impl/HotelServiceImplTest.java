package com.capgemini.hotel.Services.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.capgemini.hotel.entities.Hotel;
import com.capgemini.hotel.exceptions.ResourceNotFound;
import com.capgemini.hotel.repositories.HotelRepository;

class HotelServiceImplTest {
	@Mock
	HotelRepository hotelRepository;
	HotelServiceImpl hotelServiceImpl;
	Hotel hotel;
	AutoCloseable autoCloseable;
	
	

	@BeforeEach
	void setUp() throws Exception {
		autoCloseable=MockitoAnnotations.openMocks(this);
		hotelServiceImpl = new HotelServiceImpl(hotelRepository);
		hotel = new Hotel("1", "meat and matka","marathali" , "Odia hotel");
		
	}

	@AfterEach
	void tearDown() throws Exception {
		autoCloseable.close();
	}

	@Test
	void testCreateHotel() {
		mock(HotelRepository.class);
		mock(HotelServiceImplTest.class);
		
		when(hotelRepository.save(hotel)).thenReturn(hotel);
		assertThat(hotelServiceImpl.createHotel(hotel).getClass()).isEqualTo(Hotel.class);
		
		assertThat(hotelServiceImpl.createHotel(hotel)).isNotEqualTo(null);
	}

	@Test
	void testUpdateHotel() {
		mock(HotelRepository.class);
		mock(HotelServiceImplTest.class);
		
		when(hotelRepository.save(hotel)).thenReturn(hotel);
		when(hotelRepository.findById("1")).thenReturn(Optional.ofNullable(hotel));
		assertThat(hotelServiceImpl.updateHotel("1",hotel).getId()).isEqualTo(hotel.getId());
		
		ResourceNotFound ex = assertThrows(ResourceNotFound.class, ()->{
			hotelServiceImpl.updateHotel("2", hotel);
		});
		assertEquals(ResourceNotFound.class, ex.getClass());
		
	}

	
	@Test
	void testDeletHotel() {
		mock(HotelRepository.class);
		mock(HotelServiceImplTest.class);
		
		when(hotelRepository.findById("1")).thenReturn(Optional.ofNullable(hotel));
		assertThat(hotelServiceImpl.deletHotel("1")).isEqualTo("Hotel Data deleted with id : "+hotel.getId());
	}

	@Test
	void testGetHotelById() {
		mock(HotelRepository.class);
		mock(HotelServiceImplTest.class);
		
		when(hotelRepository.findById("1")).thenReturn(Optional.ofNullable(hotel));
		assertThat(hotelServiceImpl.getHotelById("1")).isEqualTo(hotel);
	}

	@Test
	void testGetAllHotels() {
		mock(HotelRepository.class);
		mock(HotelServiceImplTest.class);
		
		when(hotelRepository.findAll()).thenReturn(Collections.singletonList(hotel));
		assertThat(hotelServiceImpl.getAllHotels().get(0)).isEqualTo(hotel);
	}

}
