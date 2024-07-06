package com.sparta.library.loan.dto;

import lombok.Builder;

import java.time.LocalDate;


@Builder
public record LoanHistoryResponseDTO(
        Long id,
        Long bookId,
        String bookTitle,
        String bookAuthor,
        Long memberId,
        String memberName,
        String memberPhoneNumber,
        Boolean returned,
        LocalDate loanDate,
        LocalDate returnDate
) {
    public static LoanHistoryResponseDTO from(LoanHistoryResponseDTO loanHistoryResponseDTO) {
        return LoanHistoryResponseDTO.builder()
                .id(loanHistoryResponseDTO.id())
                .bookId(loanHistoryResponseDTO.bookId())
                .bookTitle(loanHistoryResponseDTO.bookTitle())
                .bookAuthor(loanHistoryResponseDTO.bookAuthor())
                .memberId(loanHistoryResponseDTO.memberId())
                .memberName(loanHistoryResponseDTO.memberName())
                .memberPhoneNumber(loanHistoryResponseDTO.memberPhoneNumber())
                .returned(loanHistoryResponseDTO.returned())
                .loanDate(loanHistoryResponseDTO.loanDate())
                .returnDate(loanHistoryResponseDTO.returnDate())
                .build();
    }
}