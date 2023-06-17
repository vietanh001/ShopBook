package com.example.cuoiky.config;

public interface SecurityService {
	
	boolean isAuthenticated();
	
	void autoLogin(String username, String password);
}
