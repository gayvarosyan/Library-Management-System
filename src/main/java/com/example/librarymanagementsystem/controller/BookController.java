package com.example.librarymanagementsystem.controller;

import com.example.librarymanagementsystem.model.Book;
import com.example.librarymanagementsystem.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;
    
    @GetMapping
    public String listBooks(@RequestParam(required = false) Long categoryId,
                           @RequestParam(required = false) String search,
                           Model model) {
        model.addAllAttributes(bookService.getListBooksModel(categoryId, search));
        return "books";
    }
    
    @GetMapping("/add")
    public String showAddBookForm(Model model) {
        model.addAllAttributes(bookService.getAddBookFormModel());
        return "add-book";
    }
    
    @PostMapping("/add")
    public String addBook(@ModelAttribute Book book, @RequestParam Long categoryId) {
        bookService.saveBook(book, categoryId);
        return "redirect:/books";
    }
    
    @GetMapping("/delete")
    public String deleteBook(@RequestParam Long id) {
        bookService.deleteBook(id);
        return "redirect:/books";
    }
}