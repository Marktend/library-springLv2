package com.sparta.library.service;

import com.sparta.library.dto.BookRequestDTO;
import com.sparta.library.dto.BookResponseDTO;

import java.util.List;

public interface BookService {
    BookResponseDTO saveBook(BookRequestDTO bookRequestDto);

    List<BookResponseDTO> getBooklist();

    BookResponseDTO getBook(Long id);

}