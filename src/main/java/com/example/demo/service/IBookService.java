package com.example.demo.service;

import com.example.demo.model.Book;

import java.util.List;

public interface IBookService extends IServiceAdapter<Book> {

    List<Book> findByCategoryId(Long categoryId);

    List<Book> findBySearch(String search);

    Book findByTitle(String title);
}
