package com.example.cuoiky.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.cuoiky.model.CartItem;
import com.example.cuoiky.repository.CartRepository;
import com.example.cuoiky.repository.ShoppingCartRepository;

@Service
public class ShoppingCartService implements ShoppingCartRepository{
	
	@Autowired
	private CartRepository cartRepository;
	
	Map<Long, CartItem> maps = new HashMap<>();
	@Override
	public void add(CartItem item) {
		CartItem cartItem = maps.get(item.getProductId());
		if(cartItem == null) {
			maps.put(item.getProductId(), item);
			/* cartRepository.save(item); */
		}else {
			cartItem.setQty(cartItem.getQty() + 1);
			/* cartRepository.save(cartItem); */
		}
	}
	
	
	@Override
	public void addDetail(CartItem item, Long qty) {
		CartItem cartItem = maps.get(item.getProductId());
		if(cartItem == null) {
			maps.put(item.getProductId(), item);
			/* cartRepository.save(item); */
		}else {
			cartItem.setQty(cartItem.getQty() + qty);
			/* cartRepository.save(cartItem); */
		}
	}
	
	@Override
	public long countInCart() {
		return maps.size();
	}
	
	@Override
	public void remove(long id) {
		maps.remove(id);
	}
	
	@Override
	public CartItem update(long proID, long qty) {
		CartItem cartItem = maps.get(proID);
		cartItem.setQty(qty);
		return cartItem;
	}
	
	@Override
	public void clear() {
		maps.clear();
	}
	
	@Override
	public Collection<CartItem> getAllItems(){
		return maps.values();
	}
	
	@Override
	public int getCount() {
		return maps.values().size();
	}
	
	@Override
	public double getAmount() {
		return maps.values().stream()
				.mapToDouble(item -> item.getQty() * item.getPrice())
				.sum();
	}
}	
