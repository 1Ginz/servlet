package com.example.demo.dao;

import com.example.demo.model.Book;
import com.example.demo.utils.paging.Pageable;

import java.util.List;

public interface IBookDao extends IAbstractDao<Book> {
    Book findOne(Long id);

    Book findByTitle(String title);

    List<Book> findAll(Pageable pageable);
    List<Book> findByCategory(Long categoryId);
    Long save(Book book);
    void update(Book book);
    void delete(Long id);
    int getTotalItem();
    List<Book> findAll();

    public List<Book> Search(String search);
}