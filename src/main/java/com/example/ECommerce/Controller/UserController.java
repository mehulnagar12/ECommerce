package com.example.ECommerce.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.ECommerce.Model.Users;
import com.example.ECommerce.Service.UserService;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/addUser")
	public Users addUser(@RequestBody Users user) {
		BCryptPasswordEncoder bCrypt = new BCryptPasswordEncoder(12);
		user.setPassword(bCrypt.encode(user.getPassword()));
		return userService.addUser(user);
	}
	
	@PostMapping("/login")
	public String login(@RequestBody Users user) {
		return userService.verify(user);
	}
}
