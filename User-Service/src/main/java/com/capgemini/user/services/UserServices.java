package com.capgemini.user.services;

import java.util.List;

import com.capgemini.user.dto.UserDto;
import com.capgemini.user.entities.User;

public interface UserServices {
	//create
	UserDto creteUser(UserDto userDto);
	//read
	UserDto getUserById(String id);
	List<UserDto> getAllUsers();
	//update
	UserDto updateUser(String id,UserDto user);
	//delete
	void deleteUser(String id);
}
