package com.icia.unity.controller;

import com.icia.unity.dto.MemberDTO;
import com.icia.unity.dto.MemberProfileDTO;
import com.icia.unity.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.IOException;

@RequestMapping("/member")
@Controller
public class MemberController {
    @Autowired
    MemberService memberService;
    @GetMapping("/save")
    public String saveForm() {
        return "memberPages/memberSave";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute MemberDTO memberDTO) throws IOException {
        memberService.save(memberDTO);
        return "memberPages/memberLogin";
    }

    @GetMapping("/login")
    public String loginForm() {
        return "memberPages/memberLogin";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute MemberDTO memberDTO, HttpSession session) {
        boolean loginResult = memberService.login(memberDTO);
        if (loginResult) {
            session.setAttribute("loginEmail", memberDTO.getMemberEmail());
            return "redirect:/board/";
        } else {
            return "redirect:/memberPages/memberLogin";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    @GetMapping("/mypage")
    public String mypage(HttpSession session, Model model) {
        String loginEmail = (String) session.getAttribute("loginEmail");
        MemberDTO memberDTO = memberService.findByMemberEmail(loginEmail);
        model.addAttribute("member", memberDTO);
        if (memberDTO.getMemberProfileAttached() == 1) {
            Long memberId = memberDTO.getId();
            MemberProfileDTO memberProfileDTO = memberService.findFile(memberId);
            model.addAttribute("profile", memberProfileDTO);
            System.out.println("memberProfileDTO = " + memberProfileDTO);
        }
        return "memberPages/memberDetail";
    }

    @GetMapping("/update")
    public String updateForm(HttpSession session, Model model) {
        String loginEmail = (String) session.getAttribute("loginEmail");
        MemberDTO memberDTO = memberService.findByMemberEmail(loginEmail);
        model.addAttribute("member", memberDTO);
        return "memberPages/memberUpdate";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute MemberDTO memberDTO, Model model) throws IOException {
        memberService.update(memberDTO);
        MemberDTO updateMember = memberService.findByMemberEmail(memberDTO.getMemberEmail());
        model.addAttribute("member", updateMember);
        return "memberPages/memberDetail";
    }

}
