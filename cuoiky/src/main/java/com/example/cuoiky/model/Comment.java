package com.example.cuoiky.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Comment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String comment;
	
	@Column(nullable = false)
	private Long userId;
	
	@Column(nullable = false)
	private String username;
	
	@Column(nullable = false)
	private LocalDateTime time;
	
	@Column(nullable = false)
	private int star;
	
	@ManyToOne
	@JoinColumn(name = "productId")
	private Book book_n;
	
}
