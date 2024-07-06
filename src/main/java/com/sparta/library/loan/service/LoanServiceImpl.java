package com.sparta.library.loan.service;

import com.sparta.library.entity.LoanEntity;
import com.sparta.library.loan.dto.LoanHistoryResponseDTO;
import com.sparta.library.loan.dto.LoanRequestDTO;
import com.sparta.library.loan.dto.LoanResponseDTO;
import com.sparta.library.loan.repository.LoanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class LoanServiceImpl implements LoanService {

    private final LoanRepository loanRepository;

    @Override
    public List<LoanResponseDTO> getLoanList() {
        // 등록된 도서 전체를 조회, 등록일 기준 오름차순

        List<LoanResponseDTO> list = loanRepository.findAll()
                .stream()
                .map(LoanResponseDTO::from)
                .collect(Collectors.toList());

        return list;
    }

    @Override
    public LoanResponseDTO loanBook(LoanRequestDTO loanRequestDTO) {
        // 책을 빌린다 == Loan 테이블에 대여 내역을 저장한다

        LoanEntity loan = LoanEntity.from(loanRequestDTO);
        try {

            // 대여상태 정리
            // returned == true  반납완료
            // returned == false 미반납

            // 대여 가능 케이스 체크 1.
            // 대여 테이블에 멤버ID가 있고, 어떤 책이라도 반납을 하지 않은 상태면 대출 불가능
            List<LoanEntity> member = loanRepository.findByMemberIdAndReturnedFalse(loan.getMemberId());
            if (member.size() != 0) {
                // 하나라도 걸리면 불가능함
                throw new IllegalArgumentException("대여 불가능 - 해당 멤버가 반납하지 않은 책이 있다");
            }

            // 대여 가능 케이스 체크 2.
            // 대여 테이블에 책ID가 있고, 반납을 하지 않은 상태면 대출 불가능
            List<LoanEntity> book = loanRepository.findByBookIdAndReturnedFalse(loan.getBookId());
            if (book.size() != 0) {
                // 하나라도 걸리면 불가능함
                throw new IllegalArgumentException("대여 불가능 - 해당 책은 이미 대여중이다");
            }

            // 대여 가능 상태면 save함
            loanRepository.save(loan);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        return loanRepository
                .findFirstByBookIdAndMemberId(loan.getBookId(), loan.getMemberId())
                .map(LoanResponseDTO::from)
                .orElseThrow(() -> new IllegalArgumentException("생성 실패"));
    }

    @Override
    public LoanResponseDTO returnBook(Long bookId) {
        // update 처리
        // 도서 반납 기능 - 선택한 도서를 반납, 대출 내역 기록의 반납상태와 반납일이 변경

        // 해당 책ID와 미반납 상태인 데이터가 있다 = 반납가능
        LoanEntity loan = loanRepository.findFirstByBookIdAndReturned(bookId, false)
                .orElseThrow(() ->
                        new IllegalArgumentException("대여중인 책이 아닙니다"));

        // 반납상태, 반납일 저장
        loan.setReturned(true);
        loan.setReturnDate(LocalDate.now());

        loanRepository.save(loan);

        return LoanResponseDTO.from(loan);
    }

    @Override
    public List<LoanHistoryResponseDTO> getLoanHistorylist(Long memberId) {
        // 멤버의 대출 기록을 모두 표시 (책, 멤버 정보 포함)
        List<LoanHistoryResponseDTO> loanHistory = loanRepository.findLoanDetailsByMemberId(memberId);

        return loanHistory.stream()
                .map(LoanHistoryResponseDTO::from)
                .collect(Collectors.toList());
    }

}