package com.example.librarymanagementsystem.controller;

import com.example.librarymanagementsystem.entity.Book;
import com.example.librarymanagementsystem.entity.Category;
import com.example.librarymanagementsystem.repository.BookRepository;
import com.example.librarymanagementsystem.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {
    
    @Autowired
    private BookRepository bookRepository;
    
    @Autowired
    private CategoryRepository categoryRepository;
    
    @GetMapping
    public String listBooks(@RequestParam(required = false) Long categoryId,
                           @RequestParam(required = false) String search,
                           Model model) {
        List<Book> books;
        
        if (categoryId != null && categoryId > 0) {
            books = bookRepository.findByCategoryId(categoryId);
        } else if (search != null && !search.isEmpty()) {
            books = bookRepository.findByTitleContainingIgnoreCase(search);
        } else {
            books = bookRepository.findAll();
        }
        
        model.addAttribute("books", books);
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("selectedCategoryId", categoryId != null ? categoryId : 0);
        model.addAttribute("searchQuery", search != null ? search : "");
        
        return "books";
    }
    
    @GetMapping("/add")
    public String showAddBookForm(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("categories", categoryRepository.findAll());
        return "add-book";
    }
    
    @PostMapping("/add")
    public String addBook(@ModelAttribute Book book, @RequestParam Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
            .orElseThrow(() -> new RuntimeException("Category not found"));
        book.setCategory(category);
        bookRepository.save(book);
        return "redirect:/books";
    }
    
    @GetMapping("/delete")
    public String deleteBook(@RequestParam Long id) {
        bookRepository.deleteById(id);
        return "redirect:/books";
    }
}