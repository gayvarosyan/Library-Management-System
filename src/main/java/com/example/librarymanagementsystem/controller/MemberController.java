package com.example.librarymanagementsystem.controller;

import com.example.librarymanagementsystem.service.MemberService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@RequestMapping("/members")
@AllArgsConstructor
public class MemberController {

    private MemberService memberService;

    @GetMapping
    public String listMembers(Model model) {
        model.addAllAttributes(memberService.getListMembersModel());
        return "members";
    }

    @GetMapping("/{id}")
    public String memberDetails(@PathVariable Long id, Model model) {
        Map<String, Object> memberModel = memberService.getMemberDetailsModel(id);
        String viewName = (String) memberModel.remove("_viewName");
        model.addAllAttributes(memberModel);
        return viewName;
    }
}