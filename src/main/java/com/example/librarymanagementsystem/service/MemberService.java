package com.example.librarymanagementsystem.service;

import java.util.Map;

public interface MemberService {
    Map<String, Object> getListMembersModel();
    Map<String, Object> getMemberDetailsModel(Long id);
}

