package com.microservice.service;

import com.microservice.dto.BookResponse;
import com.microservice.entity.Book;
import com.microservice.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SearchRecommendationServiceImpl implements SearchRecommendationService{
    private final BookRepository bookRepository;
    private final ModelMapper modelMapper;
    public SearchRecommendationServiceImpl(BookRepository bookRepository, ModelMapper modelMapper) {
        this.bookRepository = bookRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<BookResponse> searchByTitle(String title) {
        return bookRepository.findByTitleContainingIgnoreCase(title)
                .stream()
                .map(book -> modelMapper.map(book, BookResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<BookResponse> searchByAuthor(String author) {
        return bookRepository.findByAuthorContainingIgnoreCase(author)
                .stream()
                .map(book -> modelMapper.map(book, BookResponse.class))
                .collect(Collectors.toList());

    }

    @Override
    public List<BookResponse> searchByPublishedDate(LocalDate startDate, LocalDate endDate) {
        return bookRepository.findByPublishedDateBetween(startDate, endDate)
                .stream()
                .map(book -> modelMapper.map(book, BookResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public BookResponse searchByIsbn(String isbn) {
        Optional<Book> book = bookRepository.findByIsbn(isbn).stream().findFirst(); // Using `.stream()` to handle a single result gracefully
        return book.map(value -> modelMapper.map(value, BookResponse.class))
                .orElseThrow(() -> new RuntimeException("Book not found with ISBN: " + isbn));

    }

    @Override
    public List<BookResponse> searchByPriceRange(BigDecimal minPrice, BigDecimal maxPrice) {
        return bookRepository.findByPriceBetween(minPrice, maxPrice)
                .stream()
                .map(book -> modelMapper.map(book, BookResponse.class))
                .collect(Collectors.toList());
    }
}
