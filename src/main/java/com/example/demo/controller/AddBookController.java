package com.example.demo.controller;

import com.example.demo.config.TemplateEngineUtil;
import com.example.demo.model.Book;
import com.example.demo.model.Category;
import com.example.demo.service.IBookService;
import com.example.demo.service.ICategoryService;
import com.example.demo.service.impl.BookService;
import com.example.demo.service.impl.CategoryService;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/books/add")
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 5 * 5)
public class AddBookController extends HttpServlet {

    private IBookService bookService = new BookService();
    private ICategoryService categoryService = new CategoryService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(request.getServletContext());
        response.setCharacterEncoding("UTF-8");
        WebContext context = new WebContext(request, response, request.getServletContext());

        List<Category> category = (List<Category>) categoryService.getAll();
        context.setVariable("categories", category);

        engine.process("add-book.html", context, response.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException
    {
        req.setCharacterEncoding("UTF-8");
        String title = req.getParameter("title");
        String author = req.getParameter("author");
        Long categoryId = Long.valueOf(req.getParameter("category.id"));
        Double price = Double.valueOf(req.getParameter("price"));
        String releaseDate = req.getParameter("releaseDate");
        String imgCover = req.getParameter("imgcover");
        Book book = new Book();
        book.setTitle(title);
        book.setAuthor(author);
        book.setCategoryId(categoryId);
        book.setPrice(price);
        book.setReleaseDate(releaseDate);
        book.setImgCover(imgCover);
        try {
            Book update= bookService.create(book);
            System.out.println(update);
        } catch (Exception e) {
            e.printStackTrace();
        }
        RequestDispatcher dispatcher = req.getRequestDispatcher("/");
        dispatcher.forward(req, resp);
    }
}
