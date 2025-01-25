package com.microservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.dto.BookRequestDTO;
import com.microservice.dto.BookResponseDTO;
import com.microservice.service.BookService;

@RestController
@RequestMapping("/api/book")
public class BookController {

	@Autowired
	private BookService service;

	@PostMapping("/save")
	public ResponseEntity<BookResponseDTO> saveBook(@RequestBody BookRequestDTO dto) {
		BookResponseDTO book = service.createBook(dto);
		return new ResponseEntity<>(book, HttpStatus.CREATED);
	}

	@GetMapping("/get/id")
	public ResponseEntity<BookResponseDTO> getBookById(@RequestParam Long id) {
		BookResponseDTO bookById = service.getBookById(id);
		return new ResponseEntity<>(bookById, HttpStatus.OK);
	}

	@GetMapping("/getall")
	public ResponseEntity<List<BookResponseDTO>> getAllBooks() {
		return new ResponseEntity<>(service.getAllBooks(), HttpStatus.OK);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<BookResponseDTO> updateBook(@PathVariable("id") Long id, @RequestBody BookRequestDTO dto) {
		BookResponseDTO updateBook = service.updateBook(id, dto);
		return new ResponseEntity<>(updateBook, HttpStatus.OK);
	}

	@DeleteMapping("delete/{id}")
	public ResponseEntity<Void> deleteBook(@PathVariable("id") Long id) {
		service.deleteBook(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
