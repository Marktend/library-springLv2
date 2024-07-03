package com.sparta.library.repository;

import com.sparta.library.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {

    MemberEntity findByPhoneNumber(String phoneNumber);

    MemberEntity findBySsn(String ssn);
}
