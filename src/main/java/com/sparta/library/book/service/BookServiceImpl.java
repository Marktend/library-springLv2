package com.sparta.library.book.service;

import com.sparta.library.book.dto.BookRequestDTO;
import com.sparta.library.book.dto.BookResponseDTO;
import com.sparta.library.entity.BookEntity;
import com.sparta.library.book.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class BookServiceImpl implements BookService{

    private final BookRepository bookRepository;

    @Override
    public BookResponseDTO saveBook(BookRequestDTO bookRequestDTO) {
        // 제목, 저자, 언어, 출판사, 등록일을 저장

        BookEntity book = BookEntity.from(bookRequestDTO);
        try {
            bookRepository.save(book);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        return bookRepository
                .findFirstByTitleAndAuthorAndLanguageAndPublisherOrderByRegistrationDateDesc(book.getTitle(),book.getAuthor(),book.getLanguage(),book.getPublisher())
                .map(BookResponseDTO::from)
                .orElseThrow(() -> new IllegalArgumentException("생성 실패"));
    }

    @Override
    public List<BookResponseDTO> getBooklist() {
        // 등록된 도서 전체를 조회, 등록일 기준 오름차순

        List<BookResponseDTO> list = bookRepository.findAllByOrderByRegistrationDate()
                .stream()
                .map(BookResponseDTO::from)
                .collect(Collectors.toList());

        return list;
    }

    @Override
    public BookResponseDTO getBookById(Long id) {
        // 선택한 도서의 정보를 조회

        return bookRepository.findById(id)
                .map(BookResponseDTO::from)
                .orElseThrow(()-> new IllegalArgumentException("error"));
    }
}