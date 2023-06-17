package com.example.cuoiky.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.cuoiky.model.BookCategory;

@Repository
public interface BookCategoryRepository extends JpaRepository<BookCategory, Long>{

}