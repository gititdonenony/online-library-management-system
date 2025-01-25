package com.microservice.model;

import java.sql.Date;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookEntity {
	private Long id;
	private String title;
	private String author;
	private Date publishedDate;
	private String isbn;
	private Double price;

	private Date updatedAt;
	private String updatedBy;

	private Date createdAt;
	private String createdBy;
}
