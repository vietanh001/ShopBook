package com.example.cuoiky.model;


import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Bill {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private Long productId;
	
	@Column(nullable = false)
	@NotBlank(message = "Khong")
	private String address;
	
	@Column(nullable = false)
	@NotBlank(message = "Khong")
	private String phone;
	
	@Column(nullable = false)
	@NotBlank(message = "Khong")
	private String gmail;
	
	@Column(nullable = false)
	private Long qty;
	
	@Column(nullable = false)
	private LocalDateTime time;
	
	@ManyToOne
	@JoinColumn(name = "userId")
	
	private User user;


	
}
