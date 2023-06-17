package com.example.cuoiky.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.cuoiky.model.BookCategory;
import com.example.cuoiky.repository.BookCategoryRepository;


@Service
public class BookCategoryService {
	private final BookCategoryRepository bookCategoryRepository;

	public BookCategoryService(BookCategoryRepository bookCategoryRepository) {
		super();
		this.bookCategoryRepository = bookCategoryRepository;
	}
	
	public List<BookCategory> findAll(){
		return bookCategoryRepository.findAll();
	}
	
	public void createIfNotExist(BookCategory bookCategory) {
        if(bookCategoryRepository.findById(bookCategory.getId()).isPresent()) {
            return;
        }
        bookCategoryRepository.save(bookCategory);
    }
}
