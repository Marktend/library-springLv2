package com.sparta.library.loan.controller;

import com.sparta.library.loan.dto.LoanRequestDTO;
import com.sparta.library.loan.service.LoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/loan")
@RequiredArgsConstructor
public class LoanController {
    private final LoanService loanService;

    @GetMapping("/list")
    public ResponseEntity getLoanList() {
        return ResponseEntity
                .ok()
                .body(loanService.getLoanList());
    }

    @GetMapping("/history/{memberId}")
    public ResponseEntity getLoanHistory(@PathVariable Long memberId) {
        // 회원의 대출 내역 기록을 모두 표시
        return ResponseEntity
                .ok()
                .body(loanService.getLoanHistorylist(memberId));
    }

    @PostMapping
    public ResponseEntity loanBook(@RequestBody LoanRequestDTO loanRequestDTO) {
        // 책을 빌림
        return ResponseEntity
                .ok()
                .body(loanService.loanBook(loanRequestDTO));
    }

    @PutMapping("/return/{bookId}")
    public ResponseEntity returnBook(@PathVariable Long bookId) {
        // 책 반납
        return ResponseEntity
                .ok()
                .body(loanService.returnBook(bookId));
    }
}