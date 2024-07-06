package com.sparta.library.loan.repository;

import com.sparta.library.entity.LoanEntity;
import com.sparta.library.loan.dto.LoanHistoryResponseDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LoanRepository extends JpaRepository<LoanEntity, Long> {
    Optional<LoanEntity> findFirstByBookIdAndMemberId(Long bookId, Long MemberId);

    Optional<LoanEntity> findFirstByBookIdAndReturned(Long bookId, Boolean returned);

    // 멤버의 대여 기록 리스트 가져오기 + book, member Join하기
    @Query("SELECT new com.sparta.library.loan.dto.LoanHistoryResponseDTO(l.id, b.id, b.title, b.author, m.id, m.name, m.phoneNumber, l.returned, l.loanDate, l.returnDate) " +
            "FROM LoanEntity l " +
            "JOIN MemberEntity m ON l.memberId = m.id " +
            "JOIN BookEntity b ON l.bookId = b.id " +
            "WHERE l.memberId = :memberId " +
            "ORDER BY l.loanDate ASC")
    List<LoanHistoryResponseDTO> findLoanDetailsByMemberId(@Param("memberId") Long memberId);

    // 책 대여 가능 여부 체크
    List<LoanEntity> findByMemberIdAndReturnedFalse(Long memberId);

    List<LoanEntity> findByBookIdAndReturnedFalse(Long bookId);
}