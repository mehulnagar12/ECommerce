package com.example.ECommerce.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ECommerce.Model.Users;

@Repository
public interface UserRepo extends JpaRepository<Users, Integer> {
	
	Users findByUsername(String username);

}
