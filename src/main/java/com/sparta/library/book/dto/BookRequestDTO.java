package com.sparta.library.book.dto;

public record BookRequestDTO (
        Long id,
        String title,
        String author,
        String language,
        String publisher
) {}