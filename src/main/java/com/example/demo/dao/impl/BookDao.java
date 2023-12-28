package com.example.demo.dao.impl;

import com.example.demo.dao.IBookDao;
import com.example.demo.model.Book;
import com.example.demo.utils.mapper.BookMapper;
import com.example.demo.utils.paging.Pageable;
import org.apache.commons.lang.StringUtils;
//import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class BookDao extends AbstractDao<Book> implements IBookDao {


    public BookDao() {
    }

    @Override
    public Book findOne(Long id) {
        String sql = "SELECT * FROM book WHERE book.id = ?";
        List<Book> books = query(sql, new BookMapper(), id);
        return books.isEmpty() ? null : books.get(0);
    }

    @Override
    public Book findByTitle(String title) {
        String sql = "SELECT * FROM book WHERE book.title = ?";
        List<Book> books = query(sql, new BookMapper(), title);
        return books.isEmpty() ? null : books.get(0);
    }

    @Override
    public List<Book> findAll(Pageable pageable) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM book ");
        if(pageable.getSorter() != null
                && StringUtils.isNotBlank(pageable.getSorter().getSortName())
                && StringUtils.isNotBlank(pageable.getSorter().getSortBy())
        ) {
            sql.append("ODER BY ");
            sql.append(pageable.getSorter().getSortName());
            sql.append(" ");
            sql.append(pageable.getSorter().getSortBy());
            sql.append("");
        }
        if(pageable.getOffset() != null && pageable.getLimit() != null) {
            sql.append("LIMIT ");
            sql.append(pageable.getOffset());
            sql.append(", ");
            sql.append(pageable.getLimit());
            sql.append("");
        }
        return query(sql.toString(),new BookMapper());
    }

    @Override
    public List<Book> findAll(){
        String sql = "SELECT * FROM book";
        return query(sql,new BookMapper());
    }

    @Override
    public List<Book> findByCategory(Long categoryId) {
        String sql = "SELECT * FROM book WHERE category_id = ?";
        return query(sql, new BookMapper(), categoryId);
    }

    @Override
    public List<Book> Search(String search) {
        StringBuilder sql = new StringBuilder("SELECT distinct * FROM book ");
        sql.append("WHERE author like '%"+search+"%'");
        sql.append("or title like '%"+search+"%'");
        return query(sql.toString(), new BookMapper());
    }

    @Override
    public Long save(Book book) {
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO book (title, author, category_id, price, release_date, img_cover)");
//        sql.append(" VALUES(?, ?, ?, ?, ?, ?)");
        sql.append(" VALUES('");
        sql.append(book.getTitle()); sql.append("', '");
        sql.append(book.getAuthor()); sql.append("', '");
        sql.append(book.getCategoryId()); sql.append("', '");
        sql.append(book.getPrice()); sql.append("', '");
        sql.append(book.getReleaseDate()); sql.append("', '");
        sql.append(book.getImgCover()); sql.append("')");
        System.out.println(sql);
        return insert(sql.toString());
    }

    @Override
    public void update(Book book) {
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE book SET ");
        sql.append("title = '"); sql.append(book.getTitle());
        sql.append("', ");
        sql.append("author = '"); sql.append(book.getAuthor());
        sql.append("', ");
        sql.append("category_id = '"); sql.append(book.getCategoryId());
        sql.append("', ");
        sql.append("price = '"); sql.append(book.getPrice());
        sql.append("', ");
        sql.append("release_date = '"); sql.append(book.getReleaseDate());
        sql.append("', ");
        sql.append("img_cover = '"); sql.append(book.getImgCover());
        sql.append("' ");
//        sql.append("(title = ?, author = ?, category_id = ?, price = ?, release_date = ?, img_cover = ?) ");
        sql.append("Where book.id = ");
        sql.append(book.getId());
        System.out.println(sql.toString());
//        update(sql.toString(),
//                book.getTitle(),
//                book.getAuthor(),
//                book.getCategoryId(),
//                book.getPrice(),
//                book.getReleaseDate(),
//                book.getImgCover(),
//                book.getId()
//        );
        update(sql.toString());


    }

    @Override
    public void delete(Long id) {
        StringBuilder sql = new StringBuilder("DELETE FROM book WHERE id = ");
        sql.append(id);
        update(sql.toString());
    }

    @Override
    public int getTotalItem() {
        String sql = "SELECT COUNT(*) FROM book";
        return count(sql);
    }

}
