package com.capgemini.user.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capgemini.user.entities.User;

public interface UserRepository extends JpaRepository<User, String>{

}
