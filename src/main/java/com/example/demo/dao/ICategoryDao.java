package com.example.demo.dao;

import com.example.demo.model.Category;

import java.util.List;

public interface ICategoryDao extends IAbstractDao<Category> {
    List<Category> findAll();
    Long save(Category category);

    Category findById(Long id);
}
