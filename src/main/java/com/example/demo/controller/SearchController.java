package com.example.demo.controller;


import com.example.demo.config.TemplateEngineUtil;
import com.example.demo.model.Account;
import com.example.demo.model.Book;
import com.example.demo.model.Category;
import com.example.demo.service.IBookService;
import com.example.demo.service.ICategoryService;
import com.example.demo.service.impl.BookService;
import com.example.demo.service.impl.CategoryService;
import com.example.demo.utils.SessionUtil;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/search")
public class SearchController extends HttpServlet {
    private IBookService bookService = new BookService();
    private ICategoryService categoryService = new CategoryService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(request.getServletContext());
        response.setCharacterEncoding("UTF-8");
        WebContext context = new WebContext(request, response, request.getServletContext());

        String sortBy = request.getParameter("sort_by");
        String typeSort = request.getParameter("type_sort");

        context.setVariable("totalPages", 1);
        String search = request.getParameter("search");

        List<Book> books = (List<Book>) bookService.findBySearch(search);
        if("ASC".equals(typeSort)){
            books.sort(
                    (o1, o2) -> o1.getPrice().compareTo(o2.getPrice())
            );
        }
        if("DESC".equals(typeSort)){
            books.sort(
                    (o1, o2) -> o2.getPrice().compareTo(o1.getPrice())
            );
        }
        context.setVariable("books", books);
        List<Category> category = (List<Category>) categoryService.getAll();
        context.setVariable("category", category);
        Account account = (Account) SessionUtil.getInstance().getValue(request,"user");
        if (account != null){
            context.setVariable("user", account);
        } else {
            Account guest = new Account();
            guest.setRole("ROLE_GUEST");
            context.setVariable("user",guest);
        }
        engine.process("findbycategory.html", context, response.getWriter());
    }
}
