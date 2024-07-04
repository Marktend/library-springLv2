package com.sparta.library.dto;

import lombok.Builder;

@Builder
public record BookRequestDTO (
        Long id,
        String title,
        String author,
        String language,
        String publisher
) {}