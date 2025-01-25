package com.microservice.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookResponseDTO {

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
