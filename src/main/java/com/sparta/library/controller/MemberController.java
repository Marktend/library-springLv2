package com.sparta.library.controller;

import com.sparta.library.dto.MemberDTO;
import com.sparta.library.entity.MemberEntity;
import com.sparta.library.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/members")
public class MemberController {

    // 의존성 주입 (멤버 변수 선언, 생성자 정의)
    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    // 회원 등록
    @PostMapping("/join")
    public ResponseEntity<MemberEntity> joinMember(@RequestBody MemberEntity member) {
        MemberEntity joinMember = memberService.joinMember(member);
        return new ResponseEntity<>(joinMember, HttpStatus.CREATED);
    }

    // 회원조회
    @GetMapping("/{id}")
    public ResponseEntity<MemberDTO> getMemberById(@PathVariable Long id) {
        MemberDTO memberDTO = memberService.findMemberDTOById(id);
        return new ResponseEntity<>(memberDTO, HttpStatus.OK);
    }

    // 핸드폰으로 조회
    @GetMapping("/{phoneNumber}")
    public ResponseEntity<MemberDTO> getMemberByPhoneNumber(@PathVariable String phoneNumber) {
        MemberEntity member = memberService.findMemberByPhoneNumber(phoneNumber);
        MemberDTO memberDTO = memberService.convertToDTO(member);
        return new ResponseEntity<>(memberDTO, HttpStatus.OK);
    }

    // 주민번호로 조회
    @GetMapping("/ssn/{ssn}")
    public ResponseEntity<MemberDTO> getMemberBySsn(@PathVariable String ssn) {
        MemberEntity member = memberService.findBySsn(ssn);
        MemberDTO memberDTO = memberService.convertToDTO(member);
        return new ResponseEntity<>(memberDTO, HttpStatus.OK);
    }

    // 회원 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMember(@PathVariable Long id) {
        memberService.deleteMember(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
