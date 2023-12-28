package com.example.demo.controller;

import com.example.demo.config.TemplateEngineUtil;
import com.example.demo.model.Book;
import com.example.demo.model.Category;
import com.example.demo.service.ICategoryService;
import com.example.demo.service.impl.CategoryService;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.persistence.SequenceGenerator;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/category/add")
public class CategoryController extends HttpServlet {


    private ICategoryService categoryService = new CategoryService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(request.getServletContext());
        response.setCharacterEncoding("UTF-8");
        WebContext context = new WebContext(request, response, request.getServletContext());
//
        //
        engine.process("add-category.html", context, response.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        req.setCharacterEncoding("UTF-8");
        WebContext context = new WebContext(req, resp, req.getServletContext());
        Category category = new Category();
        String name = req.getParameter("name");
        category.setName(name);
        try {
            categoryService.create(category);
        } catch (Exception e) {
//            RequestDispatcher dispatcher = req.getRequestDispatcher("/demo_war_exploded/category/add");
//            dispatcher.forward(req, resp);
//
        }
        RequestDispatcher dispatcher = req.getRequestDispatcher("/");
        dispatcher.forward(req, resp);
    }
}
