package com.microservice.mapper;

import java.sql.Date;

import org.springframework.stereotype.Component;

import com.microservice.dto.BookRequestDTO;
import com.microservice.dto.BookResponseDTO;
import com.microservice.model.BookEntity;

@Component
public class BookMapper {

	public BookEntity convertRequestToEntity(BookRequestDTO dto) {
		BookEntity entity = new BookEntity();
		entity.setAuthor(dto.getAuthor());
		entity.setTitle(dto.getTitle());
		entity.setPublishedDate(dto.getPublishedDate());
		entity.setPrice(dto.getPrice());
		entity.setIsbn(dto.getIsbn());
		entity.setCreatedAt(new Date(System.currentTimeMillis()));
		entity.setUpdatedAt(new Date(System.currentTimeMillis()));
		entity.setCreatedBy("System");
		entity.setUpdatedBy("System");

		return entity;
	}

	public BookResponseDTO convertEntityToResponse(BookEntity entity) {
		BookResponseDTO dto = new BookResponseDTO();
		dto.setId(entity.getId());
		dto.setAuthor(entity.getAuthor());
		dto.setTitle(entity.getTitle());
		dto.setPublishedDate(entity.getPublishedDate());
		dto.setPrice(entity.getPrice());
		dto.setIsbn(entity.getIsbn());
		dto.setCreatedAt(entity.getCreatedAt());
		dto.setUpdatedAt(entity.getUpdatedAt());
		dto.setCreatedBy(entity.getCreatedBy());
		dto.setUpdatedBy(entity.getUpdatedBy());

		return dto;
	}

	public void updateEntityFromDTO(BookRequestDTO dto, BookEntity entity) {
		entity.setAuthor(dto.getAuthor());
		entity.setTitle(dto.getTitle());
		entity.setPublishedDate(dto.getPublishedDate());
		entity.setPrice(dto.getPrice());
		entity.setIsbn(dto.getIsbn());
		entity.setUpdatedAt(new Date(System.currentTimeMillis()));
		entity.setUpdatedBy("System");
	}
}
