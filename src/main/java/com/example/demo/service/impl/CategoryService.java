package com.example.demo.service.impl;

import com.example.demo.dao.ICategoryDao;
import com.example.demo.dao.impl.CategoryDao;
import com.example.demo.model.Category;
import com.example.demo.service.ICategoryService;
import com.example.demo.utils.paging.Pageable;

import java.util.Collection;
import java.util.List;

public class CategoryService implements ICategoryService {

    private ICategoryDao categoryDao;

    public CategoryService() {
        this.categoryDao = new CategoryDao();
    }

    @Override
    public Category create(Category dto) throws Exception {
        Long id = categoryDao.save(dto);
        return categoryDao.findById(id);
    }

    @Override
    public Category update(Long id, Category dto) throws Exception {
        return null;
    }

    @Override
    public Category getById(Long id) {
        return categoryDao.findById(id);
    }

    @Override
    public void deleteById(Long id) throws Exception {

    }

    @Override
    public List<Category> getAll() {
        return categoryDao.findAll();
    }

    @Override
    public Collection<Category> getAll(Pageable pageable) {
        return null;
    }
}
