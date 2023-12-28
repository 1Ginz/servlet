package com.example.demo.utils.mapper;

import com.example.demo.model.Category;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoryMapper implements RowMapper<Category> {

    @Override
    public Category mapRow(ResultSet rs) {
        try {
            Category category = new Category();
            category.setId(rs.getLong("id"));
            category.setName(rs.getString("name"));
            return category;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
