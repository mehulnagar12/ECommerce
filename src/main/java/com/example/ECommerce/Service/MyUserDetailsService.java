package com.example.ECommerce.Service;


import java.util.Collection;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.ECommerce.Model.Users;
import com.example.ECommerce.Repository.UserRepo;


@Service
public class MyUserDetailsService implements UserDetailsService{
	
	private UserRepo userRepo;
	
	@Autowired
	MyUserDetailsService(UserRepo userRepo) {
		this.userRepo = userRepo;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users user = userRepo.findByUsername(username);
		return new UserDetails() {
			
			@Override
			public String getUsername() {
				return user.getUsername();
			}
			
			@Override
			public String getPassword() {
				return user.getPassword();
			}
			
			@Override
			public Collection<? extends GrantedAuthority> getAuthorities() {
				return Collections.singleton(new SimpleGrantedAuthority("ADMIN"));
			}
		};
	}
}
