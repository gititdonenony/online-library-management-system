package com.microservice.controller;

import com.microservice.dto.BookResponse;
import com.microservice.service.SearchRecommendationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/search")
public class BookController {
    private final SearchRecommendationService searchRecommendationService;

    public BookController(SearchRecommendationService searchRecommendationService) {
        this.searchRecommendationService = searchRecommendationService;
    }

    @GetMapping("/title")
    public List<BookResponse> searchByTitle(@RequestParam String title) {
        return searchRecommendationService.searchByTitle(title);
    }

    @GetMapping("/author")
    public List<BookResponse> searchByAuthor(@RequestParam String author) {
        return searchRecommendationService.searchByAuthor(author);
    }

    @GetMapping("/date")
    public List<BookResponse> searchByPublishedDate(@RequestParam LocalDate startDate, @RequestParam LocalDate endDate) {
        return searchRecommendationService.searchByPublishedDate(startDate, endDate);
    }

    @GetMapping("/isbn")
    public BookResponse searchByIsbn(@RequestParam String isbn) {
        return searchRecommendationService.searchByIsbn(isbn);
    }

    @GetMapping("/price")
    public List<BookResponse> searchByPriceRange(@RequestParam BigDecimal minPrice, @RequestParam BigDecimal maxPrice) {
        return searchRecommendationService.searchByPriceRange(minPrice, maxPrice);
    }
}