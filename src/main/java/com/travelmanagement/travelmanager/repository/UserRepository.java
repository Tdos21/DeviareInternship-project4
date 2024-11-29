package com.travelmanagement.travelmanager.repository;


import org.springframework.data.jpa.repository.JpaRepository;



import com.travelmanagement.travelmanager.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	
	User findByEmail(String email);


}
