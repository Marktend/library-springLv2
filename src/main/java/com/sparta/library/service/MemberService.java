package com.sparta.library.service;

import com.sparta.library.dto.MemberDTO;
import com.sparta.library.entity.MemberEntity;
import com.sparta.library.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // 회원 정보 저장
    public MemberEntity joinMember(MemberEntity member) {

        return memberRepository.save(member);
    }

    // 아이디 값을 받아서 아이디 조회
    public MemberEntity findMemberById(Long id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 id의 회원을 찾을 수 없습니다."));
    }

    // 핸드폰 번호 조회
    public MemberEntity findMemberByPhoneNumber(String phoneNumber) {
        return memberRepository.findByPhoneNumber(phoneNumber);
    }

    // 주민번호 조회
    public MemberEntity findBySsn(String ssn) {
        return memberRepository.findBySsn(ssn);
    }

    // 아이디 값을 받아 삭제 반환x
    public void deleteMember(Long id) {
        memberRepository.deleteById(id);
    }


    //아이디 값을 입력했을때 ssn을 제외한 값을 반환받기 위함 (DTO엔 ssn없음)
    public MemberDTO findMemberDTOById(Long id) {
        MemberEntity member = findMemberById(id);
        return convertToDTO(member);
    }


    public MemberDTO convertToDTO(MemberEntity member) {
        MemberDTO dto = new MemberDTO();
        dto.setId(member.getId());
        dto.setName(member.getName());
        dto.setGender(member.getGender());
        dto.setPhoneNumber(member.getPhoneNumber());
        dto.setAddress(member.getAddress());
        return dto;
    }


}
