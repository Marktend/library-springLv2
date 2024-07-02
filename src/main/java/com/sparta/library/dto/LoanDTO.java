package com.sparta.library.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoanDTO {
    private Long id;
    private Long bookId;
    private Long memberId;
    private boolean returned;
    private LocalDate loanDate;
    private LocalDate returnDate;
    private String bookTitle;
    private String memberName;
}