package com.example.cuoiky.repository;

import java.util.Collection;

import com.example.cuoiky.model.CartItem;

public interface ShoppingCartRepository {
	
	double getAmount();

	int getCount();

	Collection<CartItem> getAllItems();

	void clear();

	CartItem update(long proID, long qty);

	void remove(long id);

	void add(CartItem item);
	
	void addDetail(CartItem item, Long qty);
	
	long countInCart();
}
