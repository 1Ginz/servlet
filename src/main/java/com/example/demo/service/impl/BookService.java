package com.example.demo.service.impl;

import com.example.demo.dao.IBookDao;
import com.example.demo.dao.ICategoryDao;
import com.example.demo.dao.impl.BookDao;
import com.example.demo.dao.impl.CategoryDao;
import com.example.demo.model.Book;
import com.example.demo.service.IBookService;
import com.example.demo.utils.paging.PageRequest;
import com.example.demo.utils.paging.Pageable;

import javax.annotation.ManagedBean;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.Collection;
import java.util.List;

public class BookService implements IBookService {


    private IBookDao bookDao;


    private ICategoryDao categoryDao;

    public BookService() {
        this.bookDao = new BookDao();
        this.categoryDao = new CategoryDao();
    }

    @Override
    public Book create(Book dto) throws Exception {
        Long id = bookDao.save(dto);
        return bookDao.findOne(id);
    }

    @Override
    public Book update(Long id, Book dto) throws Exception {
        bookDao.update(dto);
        return bookDao.findOne(id);
    }

    @Override
    public Book findByTitle(String title){
        return bookDao.findByTitle(title);
    }

    @Override
    public Book getById(Long id) {
        return bookDao.findOne(id);
    }

    @Override
    public void deleteById(Long id) throws Exception {
        bookDao.delete(id);
    }

    @Override
    public List<Book> getAll() {
        return bookDao.findAll();
    }

    @Override
    public List<Book> getAll(Pageable pageable) {
        return bookDao.findAll(pageable);
    }

    @Override
    public List<Book> findByCategoryId(Long categoryId) {
        return bookDao.findByCategory(categoryId);
    }
    @Override
    public List<Book> findBySearch(String search) {
        return bookDao.Search(search);
    }

}