package com.example.cuoiky.model;

import java.sql.Date;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;

import lombok.Data;


@Entity
@Data
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String title;
	
	@Column(nullable = false)
	private String author;
	
	@Column(nullable = false)
	private String description;
	
	
	
//	@DateTimeFormat(pattern = "MM/dd/yyyy")
	private Date date;
	private int pages;
	private String photos;
	
	@ManyToOne
	@JoinColumn(name = "category_id")
	private BookCategory category;
	
	@Column(nullable = false)
	private Double price;
	
	@Column(nullable = false)
	private int number;
	
	@OneToMany(mappedBy = "book_n", cascade = CascadeType.ALL)
	private Collection<Comment> comments;
	
	public String getPhotoPath() {
		if(photos == null || id == null)
			return null;
		return "/book-photos/" + id + "/" + photos;
	}
}
