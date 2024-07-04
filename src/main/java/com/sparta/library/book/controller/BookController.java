package com.sparta.library.controller;

import com.sparta.library.dto.BookRequestDTO;
import com.sparta.library.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @GetMapping("/list")
    public ResponseEntity getBookList(){
        return ResponseEntity
                .ok()
                .body(bookService.getBooklist());
    }

    @GetMapping("/{id}")
    public ResponseEntity getBook(@PathVariable Long id){

        return ResponseEntity
                .ok()
                .body(bookService.getBook(id));
    }

    @PostMapping
    public ResponseEntity saveBook(@RequestBody BookRequestDTO bookRequestDTO){
        return ResponseEntity
                .ok()
                .body(bookService.saveBook(bookRequestDTO));
    }
}