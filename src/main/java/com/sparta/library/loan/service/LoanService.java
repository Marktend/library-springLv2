package com.sparta.library.loan.service;

import com.sparta.library.loan.dto.LoanHistoryResponseDTO;
import com.sparta.library.loan.dto.LoanRequestDTO;
import com.sparta.library.loan.dto.LoanResponseDTO;

import java.util.List;

public interface LoanService {

    List<LoanResponseDTO> getLoanList();

    // 대출내역을 기록 (insert)
    LoanResponseDTO loanBook(LoanRequestDTO loanRequestDto);

    // 도서 반납 처리 (update)
    LoanResponseDTO returnBook(Long bookId);

    // 회원 대출내역 조회 기능
    List<LoanHistoryResponseDTO> getLoanHistorylist(Long memberId);

}