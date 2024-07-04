package com.sparta.library.book.service;

import com.sparta.library.book.dto.BookRequestDTO;
import com.sparta.library.book.dto.BookResponseDTO;

import java.util.List;

public interface BookService {
    BookResponseDTO saveBook(BookRequestDTO bookRequestDto);

    List<BookResponseDTO> getBooklist();

    BookResponseDTO getBookById(Long id);
//    BookResponseDTO getBookByTitle(String title);
}