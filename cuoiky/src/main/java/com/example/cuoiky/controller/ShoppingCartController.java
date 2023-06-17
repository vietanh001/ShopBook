package com.example.cuoiky.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.cuoiky.config.SecurityCheckRole;
import com.example.cuoiky.model.Book;
import com.example.cuoiky.model.CartItem;
import com.example.cuoiky.service.BookService;
import com.example.cuoiky.service.ShoppingCartService;

@Controller
public class ShoppingCartController {
	@Autowired
	BookService bookService;
	
	@Autowired
	ShoppingCartService cartService;
	
	@Autowired
	ShoppingCartService shoppingCartService;
	
	@Autowired
	SecurityCheckRole securityCheckRole;
	
	@GetMapping("/cart")
	public String viewCarts(Model model) {
		System.out.println(securityCheckRole.getFirstName() + " " + securityCheckRole.getLastName());
		model.addAttribute("firstname", securityCheckRole.getFirstName());
		model.addAttribute("lastname", securityCheckRole.getLastName());
		model.addAttribute("items", cartService.getAllItems());
		model.addAttribute("total", cartService.getAmount());
		model.addAttribute("countCart", shoppingCartService.countInCart());
		return "cart";
	}
	
	@GetMapping("add/{id}")
	public String addCart(@PathVariable("id") Long id) {
		Book book = bookService.findById(id);
		if(book!=null) {
			CartItem item = new CartItem();
			item.setProductId(book.getId());
			item.setName(book.getTitle());
			item.setPrice(book.getPrice());
			item.setQty(1);
			item.setImage(book.getPhotoPath());
			cartService.add(item);
		}
		return "redirect:/";
	}
	
	@GetMapping("shop/add/{id}")
	public String addCartShop(@PathVariable("id") Long id) {
		Book book = bookService.findById(id);
		if(book!=null) {
			CartItem item = new CartItem();
			item.setProductId(book.getId());
			item.setName(book.getTitle());
			item.setPrice(book.getPrice());
			item.setQty(1);
			item.setImage(book.getPhotoPath());
			cartService.add(item);
		}
		return "redirect:/shop";
	}
	
	
	@GetMapping("shop/detail/add/{id}")
	public String addCartDetail(@PathVariable("id") Long id, @RequestParam("qty") Long qty) {
		Book book = bookService.findById(id);
		if(book!=null) {
			CartItem item = new CartItem();
			item.setProductId(book.getId());
			item.setName(book.getTitle());
			item.setPrice(book.getPrice());
			item.setQty(qty);
			item.setImage(book.getPhotoPath());
			cartService.addDetail(item, qty);
		}
		return "redirect:/cart";
	}
	
	
	@GetMapping("clear")
	public String clearCart() {
		cartService.clear();
		return "redirect:/cart";
	}
	
	@GetMapping("del/{id}")
	public String removeCart(@PathVariable("id") Long id) {
		cartService.remove(id);
		return "redirect:/cart";
	}
	
	@PostMapping("update")
	public String update(@RequestParam("id") Long id, @RequestParam("qty") Long qty) {
		cartService.update(id, qty);
		return "redirect:/cart";
	}
}
