package com.example.librarymanagementsystem.service;

import com.example.librarymanagementsystem.model.Book;

import java.util.Map;

public interface BookService {
    Map<String, Object> getListBooksModel(Long categoryId, String search);
    Map<String, Object> getAddBookFormModel();
    void saveBook(Book book, Long categoryId);
    void deleteBook(Long id);
}
