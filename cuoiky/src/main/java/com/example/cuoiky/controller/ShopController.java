package com.example.cuoiky.controller;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.cuoiky.config.SecurityCheckRole;
import com.example.cuoiky.model.Bill;
import com.example.cuoiky.model.Book;
import com.example.cuoiky.model.CartItem;
import com.example.cuoiky.model.Comment;
import com.example.cuoiky.model.User;
import com.example.cuoiky.repository.UserRepository;
import com.example.cuoiky.service.BookService;
import com.example.cuoiky.service.ShoppingCartService;
import com.example.cuoiky.validator.BillValidator;

@Controller
public class ShopController {
	
	@Autowired
	ShoppingCartService cartService;
	
	@Autowired
	ShoppingCartService shoppingCartService;
	
	@Autowired
	BookService bookService;
	
	@Autowired
	SecurityCheckRole securityCheckRole;
	
	@Autowired
	UserRepository userRepository;
	
	
	@Autowired
	BillValidator billValidator;
	
	public ShopController(BookService bookService, ShoppingCartService shoppingCartService) {
		super();
		this.bookService = bookService;
		this.shoppingCartService = shoppingCartService;
	}
	
	
	@GetMapping({"/"})
	public String home(Model model) {
		model.addAttribute("books", bookService.findAll());
		model.addAttribute("countCart", shoppingCartService.countInCart());
		return "index";
	}
	
	@GetMapping({"/shop"})
	public String shop(Model model) {
		model.addAttribute("books", bookService.findAll());
		model.addAttribute("countCart", shoppingCartService.countInCart());
		return "shop";
	}
	
	
	/*
	 * @GetMapping({ "/bill" }) public String checkout(Model model) {
	 * model.addAttribute("firstname", securityCheckRole.getFirstName());
	 * model.addAttribute("lastname", securityCheckRole.getLastName());
	 * model.addAttribute("items", cartService.getAllItems());
	 * model.addAttribute("total", cartService.getAmount());
	 * model.addAttribute("countCart", shoppingCartService.countInCart()); return
	 * "checkout"; }
	 */
	
	@GetMapping("/shop/detail/{id}")
	public String viewBook(@PathVariable Long id, Model model) {
		model.addAttribute("book", bookService.findById(id));
		Book book = bookService.findById(id);
		List<String> usernames = new ArrayList<>();
		for (Comment com : book.getComments()) {
			String u = userRepository.findById(com.getUserId()).get().getUsername();
			usernames.add(u);
		}
		model.addAttribute("usernames", usernames);
		model.addAttribute("countCart", shoppingCartService.countInCart());
		return "detail";
	}
	
	@GetMapping("/shop/search")
	public String viewSeachProduct(Model model, @RequestParam(name = "key", required = false) String key) {
		List<Book> list = new ArrayList<>();
		if (!key.equals("")) {
			model.addAttribute("listBooks", bookService.seach(key));
		} else {
			model.addAttribute("listBooks", bookService.findAll());
		}
		return "shop_search";
	}
}
