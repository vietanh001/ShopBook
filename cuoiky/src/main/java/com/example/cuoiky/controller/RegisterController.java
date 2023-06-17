package com.example.cuoiky.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.cuoiky.config.SecurityService;
import com.example.cuoiky.model.User;
import com.example.cuoiky.service.UserService;
import com.example.cuoiky.validator.UserValidator;


@Controller
public class RegisterController {
	private final UserService userService;
	
	private final SecurityService securityService;
	
	private final UserValidator userValidator; 

	public RegisterController(UserService userService, SecurityService securityService, UserValidator userValidator) {
		this.userService = userService;
		this.securityService = securityService;
		this.userValidator = userValidator; 
	}
	
	@GetMapping("/registrantion")
	public String registration(Model model) {
		if(securityService.isAuthenticated()) {
			return "redirect:/";
		}
		model.addAttribute("userForm", new User());
		return "registrantion";
	}
	
	@PostMapping("/registrantion") 
	public String registration(@ModelAttribute("userForm") User  userForm, BindingResult bindingResult) {
		userValidator.validate(userForm, bindingResult); 
		if (bindingResult.hasErrors()) {
			return "registrantion";
		}
		
		userService.save(userForm);
		
		securityService.autoLogin(userForm.getUsername(), userForm.getPassword());
		
		return "redirect:/";
	}
}
