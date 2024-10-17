package com.shard.controller;

import com.shard.model.Book;
import com.shard.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/{id}")
    public Book getById(@PathVariable long id) {
        System.out.println("fetch books for id: "+ id);
        return bookService.getById(id);
    }

    @PostMapping
    public Book save(@RequestBody Book book) {
        System.out.println("save books for id: " + book.getId());
        return bookService.save(book);
    }
}
