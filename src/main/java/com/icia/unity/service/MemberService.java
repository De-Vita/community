package com.icia.unity.service;

import com.icia.unity.dto.MemberDTO;
import com.icia.unity.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
    @Autowired
    MemberRepository memberRepository;
    public int save(MemberDTO memberDTO) {
        if (memberDTO.getMemberProfile().isEmpty()) {
            System.out.println("파일없음");
            memberDTO.setMemberProfileAttached(0);
        } else {
            System.out.println("파일있음");
            memberDTO.setMemberProfileAttached(1);
        }
        return memberRepository.save(memberDTO);
    }


}
