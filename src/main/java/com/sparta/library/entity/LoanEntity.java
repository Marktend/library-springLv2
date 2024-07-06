package com.sparta.library.entity;

import com.sparta.library.loan.dto.LoanRequestDTO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@SuperBuilder
@Data
@Table(name = "loan_table")
@EntityListeners(AuditingEntityListener.class)
public class LoanEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "book_id", nullable = false)
    private Long bookId;

    @JoinColumn(name = "member_id", nullable = false)
    private Long memberId;

    private Boolean returned;
    private LocalDate loanDate;
    private LocalDate returnDate;

    public static LoanEntity from(LoanRequestDTO loanRequestDto) {
        return LoanEntity.builder()
                .id(loanRequestDto.id())
                .bookId(loanRequestDto.bookId())
                .memberId(loanRequestDto.memberId())
                .returned(loanRequestDto.returned())
                .loanDate(loanRequestDto.loanDate())
                .returnDate(loanRequestDto.returnDate())
                .build();
    }
}