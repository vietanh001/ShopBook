package com.example.cuoiky.service;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.example.cuoiky.model.Book;
import com.example.cuoiky.repository.BookRepository;


@Service
public class BookService {
	private final BookRepository bookRepository;

	public BookService(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}
	
	public List<Book> findByTitleContaining(String title) {
		return bookRepository.findByTitleContaining(title);
	}
	
	public List<Book> seach(String key) {
		return bookRepository.seach(key);
	}

	public Book saveBook(Book book) {
		return bookRepository.save(book);
	}
	
	public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public Book findByBookTitle(String title) {
        return bookRepository.findByTitle(title);
    }

    public Book findById(Long id) {
        return bookRepository.findById(id).get();
    }

    public void deleteBookById(Long id) {
        bookRepository.deleteById(id);
    }

    public Book updateBook(Book existingBook) {
        return bookRepository.save(existingBook);
    }
    
}