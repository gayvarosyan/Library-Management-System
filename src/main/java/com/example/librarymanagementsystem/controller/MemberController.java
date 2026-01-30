package com.example.librarymanagementsystem.controller;

import com.example.librarymanagementsystem.entity.Member;
import com.example.librarymanagementsystem.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/members")
public class MemberController {
    
    @Autowired
    private MemberRepository memberRepository;
    
    @GetMapping
    public String listMembers(Model model) {
        model.addAttribute("members", memberRepository.findAll());
        return "members";
    }
    
    @GetMapping("/{id}")
    public String memberDetails(@PathVariable Long id, Model model) {
        Optional<Member> member = memberRepository.findById(id);
        if (member.isPresent()) {
            model.addAttribute("member", member.get());
            return "member-details";
        }
        return "redirect:/members";
    }
}