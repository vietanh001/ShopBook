package com.example.cuoiky.config;

import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.cuoiky.model.User;
import com.example.cuoiky.repository.UserRepository;



@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	private final UserRepository userRepository;

	public UserDetailsServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) {
		User user = userRepository.findByUsername(username);
		if(user == null) throw new UsernameNotFoundException(username);
		
		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
		
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities);
	}
}
