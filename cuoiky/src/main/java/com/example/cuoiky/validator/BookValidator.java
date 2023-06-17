package com.example.cuoiky.validator;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.example.cuoiky.model.Book;
import com.example.cuoiky.service.BookService;



@Component
public class BookValidator implements Validator{
	private final BookService bookService;

	public BookValidator(BookService bookService) {
		this.bookService = bookService;
	}
	
	@Override
	public boolean supports(Class<?> aClass) {
		return Book.class.equals(aClass);
	}
	

	@Override
	public void validate(Object o, Errors errors) {
		Book book = (Book) o;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "NotEmptyTD");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "author", "NotEmptyTG");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "NotEmptyMTS");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "date", "NotEmptyN"); 
		
		Long bookId = book.getId();
		if(bookId != null) {
			Book b = bookService.findById(bookId);
			if(!b.getTitle().equals(book.getTitle())) {
				if(bookService.findByBookTitle(book.getTitle()) != null) {
					errors.rejectValue("title", "DuplicateBook");
				}
			}
		}
		else {
			if(bookService.findByBookTitle(book.getTitle()) != null) {
				errors.rejectValue("title", "DuplicateBook");
			}
		}
	}
}

