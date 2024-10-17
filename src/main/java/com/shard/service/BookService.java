package com.shard.service;

import com.shard.config.DataSourceContextHolder;
import com.shard.model.Book;
import com.shard.repository.BookRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BookService {

    @Autowired
    private BookRepo bookRepo;

    public Book getById(long id) {
//        TODO :: can update this logic
        String shard = "shard"+((id % 3)+1);
        System.out.println("Books and shardId -- " + id + " " + shard);
        DataSourceContextHolder.setDataSourceKey(shard);
        Book book = bookRepo.findById(id).orElse(null);
        DataSourceContextHolder.clear();
        return book;
    }

    public Book save(Book book) {
        String shard = "shard"+((book.getId() % 3)+1);
        System.out.println("Books and shardId -- " + book.getId() + " " + shard);
        DataSourceContextHolder.setDataSourceKey(shard);
        Book updatedBook = bookRepo.save(book);
        DataSourceContextHolder.clear();
        return book;
    }

}