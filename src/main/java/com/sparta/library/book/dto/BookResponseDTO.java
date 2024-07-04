package com.sparta.library.dto;

import com.sparta.library.entity.BookEntity;
import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Builder
public record BookResponseDTO (
        Long id,
        String title,
        String author,
        String language,
        String publisher,
        LocalDate registrationDate
) {
    public static BookResponseDTO from(BookEntity book) {
        return BookResponseDTO.builder()
                .id(book.getId())
                .title(book.getTitle())
                .author(book.getAuthor())
                .language(book.getLanguage())
                .publisher(book.getPublisher())
                .registrationDate(book.getRegistrationDate())
                .build();
    }
}