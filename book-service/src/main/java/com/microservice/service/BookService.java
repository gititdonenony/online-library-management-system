package com.microservice.service;

import java.util.List;

import com.microservice.dto.BookRequestDTO;
import com.microservice.dto.BookResponseDTO;

public interface BookService {
	BookResponseDTO createBook(BookRequestDTO bookDTO);

	BookResponseDTO getBookById(Long id);

	List<BookResponseDTO> getAllBooks();

	BookResponseDTO updateBook(Long id, BookRequestDTO bookDTO);

	void deleteBook(Long id);
}
