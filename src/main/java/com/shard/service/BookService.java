package com.shard.service;

import com.shard.model.Book;
import com.shard.repository.BookRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class BookService {

    @Autowired
    private BookRepo bookRepo;

    public Book getById(long id) {
        System.out.println("Books -- " + id);
        Optional<Book> book = bookRepo.findById(id);
        return book.orElse(null);
    }

    public Book save(Book book) {
        System.out.println("Books post -- " + book);
        return bookRepo.save(book);
    }

}