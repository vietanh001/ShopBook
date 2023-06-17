package com.example.cuoiky.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.cuoiky.config.SecurityService;


@Controller
public class LoginController {
	private final SecurityService securityService;
	
	@Autowired
	public LoginController(SecurityService securityService) {
		this.securityService = securityService;
	}
	
	@GetMapping("/login")
	public String login(Model model, String error, String logout) {
		if(securityService.isAuthenticated()) {
			return "redirect:/";
		}
		if(error != null) {
			model.addAttribute("error", "Tài khoản và mật khẩu chưa đúng!");
		}
		if(logout != null) {
			model.addAttribute("message", "Đăng xuất thành công!");
			return "redirect:/";
		}
		return "login";
	}
}
