package com.microservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookRequest {
    private String title;
    private String author;
    private LocalDate publishedDate;
    private String isbn;
    private BigDecimal price;
}
