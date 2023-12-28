package com.example.demo.dao;

import com.example.demo.utils.mapper.RowMapper;

import java.util.List;

public interface IAbstractDao<T> {
    <T> List<T> query(String sql, RowMapper<T> rowMapper, Object... parameters);
    void update(String sql, Object... parameters);
    Long insert (String sql, Object... parameters);
    int count(String sql, Object... parameters);
}
