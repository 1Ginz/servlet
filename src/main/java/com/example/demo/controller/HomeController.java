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
import com.example.demo.utils.paging.PageRequest;
import com.example.demo.utils.paging.Pageable;
import com.example.demo.utils.sort.Sorter;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet("/")
public class HomeController extends HttpServlet {


    private IBookService bookService = new BookService();
    private ICategoryService categoryService = new CategoryService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(request.getServletContext());
        response.setCharacterEncoding("UTF-8");
        WebContext context = new WebContext(request, response, request.getServletContext());

        String pageNumber = request.getParameter("pageNumber");
        String limitParam = request.getParameter("limitParam");
        String sortBy = request.getParameter("sort_by");
        String typeSort = request.getParameter("type_sort");
        int currentPage = pageNumber == null ? 0 : Integer.valueOf(pageNumber);
        int limit = limitParam == null ? 15 : Integer.valueOf(limitParam);

        context.setVariable("pageNumber", currentPage);
        context.setVariable("limit", limit);
        int total = bookService.getAll().size();
        int totalPages = total % limit != 0
               ? (int) Math.ceil(total / 15)
               : total / 15 -1;
//        System.out.println(sortBy);
//        System.out.println(typeSort);
        Pageable page = new PageRequest(currentPage,limit,null);
        context.setVariable("totalPages", totalPages);

        List<Book> books = (List<Book>) bookService.getAll(page);
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

        engine.process("index.html", context, response.getWriter());
    }

}
