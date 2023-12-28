package com.example.demo.utils.mapper;

import com.example.demo.model.Book;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookMapper implements RowMapper<Book> {

    @Override
    public Book mapRow(ResultSet rs) {
        try {
            Book book = new Book();
            book.setId(rs.getLong("id"));
            book.setTitle(rs.getString("title"));
            book.setAuthor(rs.getString("author"));
            book.setCategoryId(rs.getLong("category_id"));
            book.setPrice(rs.getDouble("price"));
            book.setReleaseDate(rs.getString("release_date"));
            book.setImgCover(rs.getString("img_cover"));
            return book;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

}
