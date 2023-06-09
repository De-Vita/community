package com.icia.unity.repository;

import com.icia.unity.dto.MemberDTO;
import com.icia.unity.dto.MemberProfileDTO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MemberRepository {
    @Autowired
    private SqlSessionTemplate sql;

    public MemberDTO save(MemberDTO memberDTO) {
        System.out.println("insert 전 = " + memberDTO);
        sql.insert("Member.save", memberDTO);
        System.out.println("insert 후 = " + memberDTO);
        return memberDTO;
    }

    public MemberDTO login(MemberDTO memberDTO) {
        return sql.selectOne("Member.login", memberDTO);
    }

    public void saveFile(MemberProfileDTO memberProfileDTO) {
        sql.insert("Member.saveFile", memberProfileDTO);
    }


    public MemberDTO findByMemberEmail(String loginEmail) {
        return sql.selectOne("Member.findByMemberEmail", loginEmail);
    }


    public MemberProfileDTO findFile(Long memberId) {
        return sql.selectOne("Member.findFile", memberId);
    }

    public MemberDTO update(MemberDTO memberDTO) {
        sql.update("Member.update", memberDTO);
        return memberDTO;
    }

    public void updateFile(MemberProfileDTO memberProfileDTO) {
        sql.update("Member.updateFile", memberProfileDTO);
    }

    public List<MemberDTO> findAll() {
        return sql.selectList("Member.findAll");
    }

    public void delete(Long id) {
        sql.delete("Member.delete", id);
    }

    public void deleteProfile(Long id) {
        sql.delete("Member.deleteProfile", id);
    }

}
