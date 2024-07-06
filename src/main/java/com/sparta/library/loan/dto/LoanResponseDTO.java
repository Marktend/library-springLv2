package com.sparta.library.loan.dto;

import com.sparta.library.entity.LoanEntity;
import lombok.Builder;

import java.time.LocalDate;


@Builder
public record LoanResponseDTO(
        Long id,
        Long bookId,
        Long memberId,
        Boolean returned,
        LocalDate loanDate,
        LocalDate returnDate
) {
    public static LoanResponseDTO from(LoanEntity loan) {
        return LoanResponseDTO.builder()
                .id(loan.getId())
                .bookId(loan.getBookId())
                .memberId(loan.getMemberId())
                .returned(loan.getReturned())
                .loanDate(loan.getLoanDate())
                .returnDate(loan.getReturnDate())
                .build();
    }
}