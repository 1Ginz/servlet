package com.example.demo.controller;

import com.example.demo.config.TemplateEngineUtil;
import com.example.demo.service.IBookService;
import com.example.demo.service.impl.BookService;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delete")
public class DeleteBookController extends HttpServlet {

    private IBookService bookService = new BookService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String id = request.getParameter("id");
        try {
            bookService.deleteById(Long.valueOf(id));
        } catch (Exception e) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/book?id="+id);
            dispatcher.forward(request, response);
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("/");
        dispatcher.forward(request, response);
    }
}
