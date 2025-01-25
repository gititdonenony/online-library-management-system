package com.microservice.repository;
import com.microservice.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByTitleContainingIgnoreCase(String title);
    List<Book> findByAuthorContainingIgnoreCase(String author);
    List<Book> findByPublishedDateBetween(LocalDate startDate, LocalDate endDate);
    List<Book> findByIsbn(String isbn);
    List<Book> findByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice);
}
