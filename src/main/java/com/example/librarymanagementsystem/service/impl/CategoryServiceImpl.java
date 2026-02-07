package com.example.librarymanagementsystem.service.impl;

import com.example.librarymanagementsystem.repository.CategoryRepository;
import com.example.librarymanagementsystem.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CategoryServiceImpl implements CategoryService {
    
    @Autowired
    private CategoryRepository categoryRepository;
    
    @Override
    public Map<String, Object> getListCategoriesModel() {
        Map<String, Object> model = new HashMap<>();
        model.put("categories", categoryRepository.findAll());
        return model;
    }
}

