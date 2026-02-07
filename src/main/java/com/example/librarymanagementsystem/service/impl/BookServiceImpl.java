package com.example.librarymanagementsystem.service.impl;

import com.example.librarymanagementsystem.model.Book;
import com.example.librarymanagementsystem.model.Category;
import com.example.librarymanagementsystem.repository.BookRepository;
import com.example.librarymanagementsystem.repository.CategoryRepository;
import com.example.librarymanagementsystem.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BookServiceImpl implements BookService {
    
    @Autowired
    private BookRepository bookRepository;
    
    @Autowired
    private CategoryRepository categoryRepository;
    
    @Override
    public Map<String, Object> getListBooksModel(Long categoryId, String search) {
        Map<String, Object> model = new HashMap<>();
        
        List<Book> books;
        if (categoryId != null && categoryId > 0) {
            books = bookRepository.findByCategoryId(categoryId);
        } else if (search != null && !search.isEmpty()) {
            books = bookRepository.findByTitleContainingIgnoreCase(search);
        } else {
            books = bookRepository.findAll();
        }
        
        model.put("books", books);
        model.put("categories", categoryRepository.findAll());
        model.put("selectedCategoryId", categoryId != null ? categoryId : 0);
        model.put("searchQuery", search != null ? search : "");
        
        return model;
    }
    
    @Override
    public Map<String, Object> getAddBookFormModel() {
        Map<String, Object> model = new HashMap<>();
        model.put("book", new Book());
        model.put("categories", categoryRepository.findAll());
        return model;
    }
    
    @Override
    public void saveBook(Book book, Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
            .orElseThrow(() -> new RuntimeException("Category not found"));
        book.setCategory(category);
        bookRepository.save(book);
    }
    
    @Override
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
}

