package com.example.cuoiky.controller;



import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.cuoiky.config.SecurityCheckRole;
import com.example.cuoiky.model.Bill;
import com.example.cuoiky.model.CartItem;
import com.example.cuoiky.model.FieldError;
import com.example.cuoiky.model.User;
import com.example.cuoiky.service.BillService;
import com.example.cuoiky.service.BookService;
import com.example.cuoiky.service.ShoppingCartService;
import com.example.cuoiky.service.UserService;
import com.example.cuoiky.validator.BillValidator;


@Controller
public class BillController {
	
	@Autowired
	SecurityCheckRole checkRole;

	@Autowired
	BookService bookService;
	
	@Autowired
	BillService billService;
	
	@Autowired
	ShoppingCartService cartService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	BillValidator billValidator;
	
	@Autowired
	ShoppingCartService shoppingCartService;
	
	
	
	
	public BillController(SecurityCheckRole checkRole, BookService bookService, BillService billService,
			ShoppingCartService cartService, UserService userService, BillValidator billValidator) {
		super();
		this.checkRole = checkRole;
		this.bookService = bookService;
		this.billService = billService;
		this.cartService = cartService;
		this.userService = userService;
		this.billValidator = billValidator;
	}
	
	
	@GetMapping({ "/bill" })
	public String checkout(Model model) {
		model.addAttribute("firstname", checkRole.getFirstName());
		model.addAttribute("lastname", checkRole.getLastName());
		model.addAttribute("items", cartService.getAllItems());
		model.addAttribute("total", cartService.getAmount());
		model.addAttribute("countCart", shoppingCartService.countInCart());
		return "checkout";
	}
	
	/* @PostMapping("/save/bill") */
	
	@PostMapping("/bill")
	public String saveBill(@ModelAttribute("bill") Bill bill, BindingResult bindingResult, Model model) throws IOException {
		billValidator.validate(bill, bindingResult); 
		model.addAttribute("firstname", checkRole.getFirstName());
		model.addAttribute("lastname", checkRole.getLastName());
		model.addAttribute("items", cartService.getAllItems());
		model.addAttribute("total", cartService.getAmount());
		model.addAttribute("countCart", shoppingCartService.countInCart());
		if(bindingResult.hasErrors()) {
			Map<String, String> errors= new HashMap<>();

            bindingResult.getFieldErrors().forEach(
                    error -> errors.put(error.getField(), error.getDefaultMessage())
            );

            String errorMsg= "";

            for(String key: errors.keySet()){
            	if(key.equals("gmail")) {
            		
					String gmail = bill.getGmail();
					int n = gmail.length();
					if (n > 10) {
						String mail = gmail.substring(n - 10, n);
						if (!mail.equals("@gmail.com")) {
							model.addAttribute("gmail2", "Chưa đúng định dạng Mail (XXX@gmail.com)");
						}
					} else {
						if (!gmail.equals("")) {
							model.addAttribute("gmail2", "Độ dài mail lớn hơn 10");
						}
						else {
							model.addAttribute("gmail", "Không để trống trường Gmail");
						}
					}

            	}
            	else if(key.equals("phone")) {
            		model.addAttribute("phone", "Không để trống trường Phone");
            		String phone = bill.getPhone();
            		if(!phone.matches("[0-9]{10}") && !phone.equals("")) {
            			model.addAttribute("phone2", "Chưa đúng định dạng SĐT");
            		}
            	}
            	else if(key.equals("address")) {
            		model.addAttribute("address", "Không để trống trường SĐT");
            	}
            	System.out.println(key);
            }
			return "checkout"; 
		}
		ArrayList<CartItem> newList = new ArrayList<>(cartService.getAllItems());
		for(CartItem cartItem:newList) {
			Bill bill2 = new Bill(null, null, null, null, null, null, null, null);
			bill2.setAddress(bill.getAddress());
			bill2.setGmail(bill.getGmail());
			bill2.setPhone(bill.getPhone());
			bill2.setProductId(cartItem.getProductId());
			bill2.setQty(cartItem.getQty());
			bill2.setTime(LocalDateTime.now());
			bill2.setUser((User) userService.findByUsername(checkRole.getUsNa()));
			billService.saveBill(bill2);  
		}
		System.out.println("Có: " + String.valueOf(shoppingCartService.countInCart()));
		shoppingCartService.clear();
		return "redirect:/";
	}
}
