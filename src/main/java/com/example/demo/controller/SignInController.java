package com.example.demo.controller;

import com.example.demo.config.TemplateEngineUtil;
import com.example.demo.model.Account;
import com.example.demo.service.IAccountService;
import com.example.demo.service.impl.AccountService;
import com.example.demo.utils.SessionUtil;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class SignInController extends HttpServlet {

    private IAccountService accountService;

    public SignInController(){
        this.accountService = new AccountService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(request.getServletContext());
        response.setCharacterEncoding("UTF-8");
        WebContext context = new WebContext(request, response, request.getServletContext());
//
        //
        engine.process("sign-in.html", context, response.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException
    {
        req.setCharacterEncoding("UTF-8");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        Account account = accountService.findByEmail(email,password);
        if(account != null){
            SessionUtil.getInstance().putValue(req,"user",account);
            resp.sendRedirect(req.getContextPath() + "/");
        } else {
            resp.sendRedirect(req.getContextPath() + "/login");
        }
    }

}
