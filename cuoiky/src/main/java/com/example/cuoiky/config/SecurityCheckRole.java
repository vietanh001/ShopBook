package com.example.cuoiky.config;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.example.cuoiky.model.User;
import com.example.cuoiky.repository.UserRepository;
import com.example.cuoiky.service.UserService;

@Service
public class SecurityCheckRole {
	
	private final UserService userService;

	
    public SecurityCheckRole(UserService userService) {
		super();
		this.userService = userService;
	}


	public String getRole() {
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String username = userDetails.getUsername();
		User user = userService.findByUsername(username);
		return user.getUserRole();
    }
	
	public String getFirstName() {
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String username = userDetails.getUsername();
		User user = userService.findByUsername(username);
		return user.getFirstName();
    }
	
	public String getLastName() {
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String username = userDetails.getUsername();
		User user = userService.findByUsername(username);
		return user.getLastName();
    }
	
	public Long getUserId() {
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String username = userDetails.getUsername();
		User user = userService.findByUsername(username);
		return user.getId();
    }
	
	public String getUsNa() {
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String username = userDetails.getUsername();
		return username;
    }
}