package com.example.librarymanagementsystem.config;

import com.example.librarymanagementsystem.entity.Book;
import com.example.librarymanagementsystem.entity.Category;
import com.example.librarymanagementsystem.entity.Member;
import com.example.librarymanagementsystem.repository.BookRepository;
import com.example.librarymanagementsystem.repository.CategoryRepository;
import com.example.librarymanagementsystem.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataInitializer implements CommandLineRunner {
    
    @Autowired
    private CategoryRepository categoryRepository;
    
    @Autowired
    private BookRepository bookRepository;
    
    @Autowired
    private MemberRepository memberRepository;
    
    @Override
    public void run(String... args) throws Exception {
        if (categoryRepository.count() == 0) {
            Category programming = new Category();
            programming.setName("Programming");
            programming.setDescription("Programming books");
            programming = categoryRepository.save(programming);
            
            Category sciFi = new Category();
            sciFi.setName("Science Fiction");
            sciFi.setDescription("Science fiction novels");
            sciFi = categoryRepository.save(sciFi);
            
            Category history = new Category();
            history.setName("History");
            history.setDescription("Historical books");
            history = categoryRepository.save(history);
            
            Book javaBasics = new Book();
            javaBasics.setTitle("Java Basics");
            javaBasics.setAuthor("John Smith");
            javaBasics.setYear(2020);
            javaBasics.setGenre("Programming");
            javaBasics.setCategory(programming);
            javaBasics = bookRepository.save(javaBasics);
            
            Book springAction = new Book();
            springAction.setTitle("Spring in Action");
            springAction.setAuthor("Craig Walls");
            springAction.setYear(2022);
            springAction.setGenre("Programming");
            springAction.setCategory(programming);
            springAction = bookRepository.save(springAction);
            
            Book cleanCode = new Book();
            cleanCode.setTitle("Clean Code");
            cleanCode.setAuthor("Robert Martin");
            cleanCode.setYear(2008);
            cleanCode.setGenre("Programming");
            cleanCode.setCategory(programming);
            cleanCode = bookRepository.save(cleanCode);
            
            Member armen = new Member();
            armen.setFullName("Armen Sargsyan");
            armen.setEmail("armen@mail.com");
            armen.setPhone("091-123456");
            armen.setRegistrationDate(LocalDate.of(2021, 8, 10));
            armen.getBorrowedBooks().add(javaBasics);
            armen.getBorrowedBooks().add(springAction);
            armen.getBorrowedBooks().add(cleanCode);
            memberRepository.save(armen);
            
            Member ani = new Member();
            ani.setFullName("Ani Davtyan");
            ani.setEmail("ani@mail.com");
            ani.setPhone("091-654321");
            ani.setRegistrationDate(LocalDate.of(2022, 1, 15));
            ani.getBorrowedBooks().add(javaBasics);
            memberRepository.save(ani);
        }
    }
}