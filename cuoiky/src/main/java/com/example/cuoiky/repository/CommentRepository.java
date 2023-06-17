package com.example.cuoiky.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.cuoiky.model.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long>{

}
