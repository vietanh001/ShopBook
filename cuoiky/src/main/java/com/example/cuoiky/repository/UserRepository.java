package com.example.cuoiky.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.cuoiky.model.User;


public interface UserRepository extends JpaRepository<User, Long>{
	User findByUsername(String username);

}
