package com.microservice.service;

import com.microservice.dto.BookResponse;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface SearchRecommendationService {
    List<BookResponse> searchByTitle(String title);

    List<BookResponse> searchByAuthor(String author);

    List<BookResponse> searchByPublishedDate(LocalDate startDate, LocalDate endDate);

    BookResponse searchByIsbn(String isbn);

    List<BookResponse> searchByPriceRange(BigDecimal minPrice, BigDecimal maxPrice);

}
