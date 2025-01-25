package com.microservice.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.dto.BookRequestDTO;
import com.microservice.dto.BookResponseDTO;
import com.microservice.mapper.BookMapper;
import com.microservice.model.BookEntity;
import com.microservice.repository.BookRepository;
import com.microservice.service.BookService;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private BookRepository repo;

	@Autowired
	private BookMapper mapper;

	@Override
	public BookResponseDTO createBook(BookRequestDTO bookDTO) {
		if (bookDTO == null) {
			throw new RuntimeException("Request fields cannot be null");
		}
		return mapper.convertEntityToResponse(repo.save(mapper.convertRequestToEntity(bookDTO)));
	}

	@Override
	public BookResponseDTO getBookById(Long id) {
		Optional<BookEntity> byId = repo.findById(id);
		if (byId.isEmpty()) {
			throw new RuntimeException("The given ID is not present");
		}
		return mapper.convertEntityToResponse(byId.get());
	}

	@Override
	public List<BookResponseDTO> getAllBooks() {
		List<BookEntity> list = repo.findAll();
		if (list.isEmpty()) {
			return List.of(); // Return an empty list instead of null
		}
		return list.stream().map(book -> mapper.convertEntityToResponse(book)).collect(Collectors.toList());
	}

	@Override
	public BookResponseDTO updateBook(Long id, BookRequestDTO bookDTO) {
		Optional<BookEntity> existingEntityOpt = repo.findById(id);
		if (existingEntityOpt.isEmpty()) {
			throw new RuntimeException("The given ID is not present");
		}
		BookEntity existingEntity = existingEntityOpt.get();
		mapper.updateEntityFromDTO(bookDTO, existingEntity);
		BookEntity newEntity = repo.save(existingEntity);
		return mapper.convertEntityToResponse(newEntity);
	}

	@Override
	public void deleteBook(Long id) {
		Optional<BookEntity> byId = repo.findById(id);
		if (byId.isEmpty()) {
			throw new RuntimeException("The given ID is not present");
		}
		repo.delete(byId.get());
	}
}
