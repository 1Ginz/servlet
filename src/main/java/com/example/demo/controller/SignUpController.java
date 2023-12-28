package com.example.demo.controller;

import com.example.demo.config.TemplateEngineUtil;
import com.example.demo.model.Account;
import com.example.demo.model.Book;
import com.example.demo.service.IAccountService;
import com.example.demo.service.impl.AccountService;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/sign-up")
public class SignUpController extends HttpServlet {

    private IAccountService accountService;

    public SignUpController(){
        this.accountService = new AccountService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(request.getServletContext());
        response.setCharacterEncoding("UTF-8");
        WebContext context = new WebContext(request, response, request.getServletContext());
//
        //
        engine.process("sign-up.html", context, response.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException
    {
        req.setCharacterEncoding("UTF-8");
        Account account = new Account();
        account.setUsername(req.getParameter("username"));
        account.setEmail(req.getParameter("email"));
        account.setPassword(req.getParameter("password"));
        account.setRole("ROLE_USER");
        try {
            accountService.Create(account);
        } catch (Exception e) {
            e.printStackTrace();
        }
        resp.sendRedirect(req.getContextPath() + "/login");
    }

}
