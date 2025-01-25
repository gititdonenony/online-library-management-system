package com.microservice.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookRequestDTO {
	private String title;
	private String author;
	private Date publishedDate;
	private String isbn;
	private Double price;

}
