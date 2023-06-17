package com.example.cuoiky.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.cuoiky.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>{
	 Book findByTitle(String title);
	 
	 List<Book> findByTitleContaining(String title);
	 
	 @Query("SELECT b FROM Book b WHERE CONCAT(b.title, ' ', b.author) LIKE %?1%")
	 List<Book> seach(String key);
}
