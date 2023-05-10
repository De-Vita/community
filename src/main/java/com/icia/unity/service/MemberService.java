package com.icia.unity.service;

import com.icia.unity.dto.MemberDTO;
import com.icia.unity.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class MemberService {
    @Autowired
    MemberRepository memberRepository;
//    public int save(MemberDTO memberDTO) {
//        if (memberDTO.getMemberProfile().isEmpty()) {
//            System.out.println("파일없음");
//            memberDTO.setMemberProfileAttached(0);
//        } else {
//            System.out.println("파일있음");
//            memberDTO.setMemberProfileAttached(1);
//        }
//        return memberRepository.save(memberDTO);
//    }
public int save(MemberDTO memberDTO) throws IOException {
    if (memberDTO.getMemberProfile().isEmpty()) {
        System.out.println("파일없음");
        memberDTO.setMemberProfileAttached(0);
        return memberRepository.save(memberDTO);
    } else {
        System.out.println("파일있음");
        memberDTO.setMemberProfileAttached(1);
        // 프로필 사진 파일 처리
        MultipartFile profileImage = memberDTO.getMemberProfile();
        // 원본 파일 이름 가져오기
        String originalFilename = profileImage.getOriginalFilename();
        // 저장용 이름 만들기
        String storedFileName = System.currentTimeMillis() + "-" + originalFilename;
        // 저장을 위한 MemberProfileDTO 세팅
        MemberProfileDTO profileDTO = new MemberProfileDTO();
        profileDTO.setOriginalFileName(originalFilename);
        profileDTO.setStoredFileName(storedFileName);
        // 로컬에 파일 저장
        // 저장할 경로 설정 (저장할폴더+저장할이름)
        String savePath = "D:\\springframework_img\\" + storedFileName;
        // 저장처리
        profileImage.transferTo(new File(savePath));
        memberDTO.setMemberProfileDTO(profileDTO);
        return memberRepository.save(memberDTO);
    }
}



    public boolean login(MemberDTO memberDTO) {
        MemberDTO dto = memberRepository.login(memberDTO);
        if (dto != null) {
            return true;
        } else {
            return false;
        }
    }
}
