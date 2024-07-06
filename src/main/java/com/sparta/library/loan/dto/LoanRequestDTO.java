package com.sparta.library.loan.dto;

import java.time.LocalDate;

public record LoanRequestDTO(
        Long id,
        Long bookId,
        Long memberId,
        Boolean returned,
        LocalDate loanDate,
        LocalDate returnDate
) {
}