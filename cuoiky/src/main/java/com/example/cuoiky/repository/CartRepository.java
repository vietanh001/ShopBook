package com.example.cuoiky.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.cuoiky.model.CartItem;

public interface CartRepository extends JpaRepository<CartItem, Long>{

}
