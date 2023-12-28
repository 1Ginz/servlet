package com.example.demo.dao.impl;


import com.example.demo.dao.ICategoryDao;
import com.example.demo.model.Category;
import com.example.demo.utils.mapper.CategoryMapper;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.annotation.ManagedBean;
import javax.enterprise.context.RequestScoped;
import java.util.List;
@RequestScoped
public class CategoryDao extends AbstractDao<Category> implements ICategoryDao {

    public CategoryDao() {
    }

    @Override
    public List<Category> findAll() {
        String sql = "SELECT * FROM category";
        return query(sql,new CategoryMapper());
    }

    @Override
    public Long save(Category category) {
        StringBuilder sql = new StringBuilder("INSERT INTO category (name)");
        sql.append(" VALUES(?)");

//        sql.append(category.getId());
//        sql.append(",'");
//        sql.append(category.getName());
//        sql.append("')");
//        List<Object> param;
//        param.add(category.getId().);
//        param.add(category.getId())
        return insert(sql.toString(),
                category.getName());
//        query(sql.toString(), new CategoryMapper());
//        return null;
    }

    @Override
    public Category findById(Long id) {
        String sql = "SELECT * FROM category WHERE id = ?";
        List<Category> categories = query(sql,new CategoryMapper(),id);
        return categories.get(0);
    }


}