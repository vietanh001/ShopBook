package com.example.cuoiky.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.cuoiky.config.SecurityCheckRole;
import com.example.cuoiky.model.Book;
import com.example.cuoiky.service.BookCategoryService;
import com.example.cuoiky.service.BookService;
import com.example.cuoiky.util.FileUploadUtil;
import com.example.cuoiky.validator.BookValidator;

@Controller
@RequestMapping("admin")
public class BookController {

	private final SecurityCheckRole checkRole;

	private final BookService bookService;

	private final BookCategoryService bookCategoryService;
	
	private final BookValidator bookValidator;

	public BookController(SecurityCheckRole checkRole, BookService bookService,
			BookCategoryService bookCategoryService, BookValidator bookValidator) {
		super();
		this.checkRole = checkRole;
		this.bookService = bookService;
		this.bookCategoryService = bookCategoryService;
		this.bookValidator = bookValidator;
	}

	@GetMapping({ "/books" })
	public String books(Model model) {
		if (checkRole.getRole().equals("ROLE_ADMIN")) {
			model.addAttribute("books", bookService.findAll());
			return "manage";
		} else {
			return "redirect:/";
		}
	}

	@GetMapping("/books/search")
	public String viewSeach(Model model, @RequestParam(name = "key", required = false) String key) {
		List<Book> list = new ArrayList<>();
		if (!key.equals("")) {
			model.addAttribute("listBooks", bookService.seach(key));
		} else {
			model.addAttribute("listBooks", bookService.findAll());
		}
		return "manage_search";
	}

	@GetMapping("/books/new")
	public String createBookForm(Model model) {
		Book book = new Book();
		model.addAttribute("book", book);
		model.addAttribute("mode", "create");
		model.addAttribute("categories", bookCategoryService.findAll());
		return "edit";
	}

	@PostMapping("/books")
	public String saveBook(@ModelAttribute("book") Book book, Model model, BindingResult bindingResult,
			@RequestParam(value = "image") MultipartFile image) throws IOException {
		bookValidator.validate(book, bindingResult); 
		model.addAttribute("categories", bookCategoryService.findAll());
		model.addAttribute("mode", "create");

		if(bindingResult.hasErrors()) {
			return "edit";
		}

		String fileName = null;
		if (image.getOriginalFilename() != null && !image.getOriginalFilename().isEmpty()) {
			fileName = StringUtils.cleanPath(image.getOriginalFilename());
			book.setPhotos(fileName);
		}
		Book savedBook = bookService.saveBook(book);
		String uploadDir = "book-photos/" + savedBook.getId();
		String test = "image/" + fileName;

		if (fileName != null) {
			FileUploadUtil.saveFile(uploadDir, fileName, image);
			FileUploadUtil.saveFile(test, "", image);
		}

		return "redirect:/";
	}

	@GetMapping("/books/edit/{id}")
	public String editBookForm(@PathVariable Long id, Model model) {
		model.addAttribute("book", bookService.findById(id));
		model.addAttribute("categories", bookCategoryService.findAll());
		model.addAttribute("mode", "edit");
		return "edit";
	}

	@GetMapping("/books/view/{id}")
	public String viewBookForm(@PathVariable Long id, Model model) {
		model.addAttribute("book", bookService.findById(id));
		model.addAttribute("categories", bookCategoryService.findAll());
		model.addAttribute("mode", "view");
		return "edit";
	}

	@GetMapping("/books/{id}")
	public String deleteBook(@PathVariable Long id) {
		try {

			File dir = new File("E:/Java Web/BTL/book-photos/" + String.valueOf(id));
			File[] img = dir.listFiles();
			for (File file : img) {
				String i = String.valueOf(file.getAbsolutePath());
				Path pathimg = Paths.get(i);
				Files.delete(pathimg);
			}
			Path path = Paths.get("E:/Java Web/BTL/book-photos/" + String.valueOf(id));
			Files.delete(path);

		} catch (Exception e) {

		}
		bookService.deleteBookById(id);
		return "redirect:/";
	}

	@PostMapping("/books/{id}")
	public String updateBook(@PathVariable Long id, @ModelAttribute("book") Book book, BindingResult bindingResult,
			Model model, @RequestParam(value = "image") MultipartFile image) throws IOException {
		bookValidator.validate(book, bindingResult);
		model.addAttribute("categories", bookCategoryService.findAll());
		model.addAttribute("mode", "edit");

		String fileName = null;
		if (image.getOriginalFilename() != null && !image.getOriginalFilename().isEmpty()) {
			fileName = StringUtils.cleanPath(image.getOriginalFilename());
		}

		if (bindingResult.hasErrors()) {
			return "create_book";
		}

		Book existingBook = bookService.findById(id);
		existingBook.setId(id);
		existingBook.setTitle(book.getTitle());
		existingBook.setAuthor(book.getAuthor());
		existingBook.setDescription(book.getDescription());
		existingBook.setCategory(book.getCategory());
		existingBook.setPages(book.getPages());
		existingBook.setDate(book.getDate());
		existingBook.setNumber(book.getNumber());
		existingBook.setPrice(book.getPrice());

		if (fileName != null && !fileName.isEmpty()) {
			existingBook.setPhotos(fileName);
		}
		bookService.updateBook(existingBook);
		String uploadDir = "book-photos/" + book.getId();

		if (fileName != null && !fileName.isEmpty()) {
			FileUploadUtil.saveFile(uploadDir, fileName, image);
		}
		return "redirect:/";
	}

}
