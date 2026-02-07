package com.example.librarymanagementsystem.service.impl;

import com.example.librarymanagementsystem.repository.MemberRepository;
import com.example.librarymanagementsystem.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class MemberServiceImpl implements MemberService {
    
    @Autowired
    private MemberRepository memberRepository;
    
    @Override
    public Map<String, Object> getListMembersModel() {
        Map<String, Object> model = new HashMap<>();
        model.put("members", memberRepository.findAll());
        return model;
    }
    
    @Override
    public Map<String, Object> getMemberDetailsModel(Long id) {
        Map<String, Object> model = new HashMap<>();
        return memberRepository.findById(id)
            .map(member -> {
                model.put("member", member);
                model.put("_viewName", "member-details");
                return model;
            })
            .orElseGet(() -> {
                Map<String, Object> redirectModel = new HashMap<>();
                redirectModel.put("_viewName", "redirect:/members");
                return redirectModel;
            });
    }
}

