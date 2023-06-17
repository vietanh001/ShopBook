package com.example.cuoiky.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.cuoiky.model.User;
import com.example.cuoiky.repository.UserRepository;



@Service
public class UserService {
	private final UserRepository userRepository;
	
	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
		super();
		this.userRepository = userRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}
	
	public void save(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		System.out.println(user);
		userRepository.save(user);
	}
	
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}
	
	
}

