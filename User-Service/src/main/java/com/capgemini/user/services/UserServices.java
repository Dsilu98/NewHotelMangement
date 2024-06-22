package com.capgemini.user.services;

import java.util.List;

import com.capgemini.user.entities.User;

public interface UserServices {
	//create
	User creteUser(User user);
	//read
	User getUserById(String id);
	List<User> getAllUsers();
	//update
	User updateUser(String id,User user);
	//delete
	void deleteUser(String id);
}
