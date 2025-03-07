package com.example.ECommerce.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.example.ECommerce.Model.Users;
import com.example.ECommerce.Repository.UserRepo;

@Service
public class UserService {
	
	private UserRepo userRepo;
	private AuthenticationManager authManager;
	private JWTService jwtService;
	
	@Autowired
	UserService(UserRepo userRepo, AuthenticationManager authManager, JWTService jwtService){
		this.userRepo = userRepo;
		this.authManager = authManager;
		this.jwtService = jwtService;
	}
	
	
	public Users addUser(Users user) {
		return userRepo.save(user);
	}


	public String verify(Users user) {
		Authentication auth = authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
		if(auth.isAuthenticated()) {
			return jwtService.generateToken(user.getUsername());
		}
		return "Fail";
	}
	
	
}
