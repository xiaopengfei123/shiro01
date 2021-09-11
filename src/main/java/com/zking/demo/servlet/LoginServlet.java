package com.zking.demo.servlet;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("login doPost");
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try {
            subject.login(token);
            request.getSession().setAttribute("username", username);
//            request.getRequestDispatcher("/jsp/main.jsp").forward(request, response);
            response.sendRedirect(request.getContextPath()+"/main.jsp");
        } catch (AuthenticationException e) {
            request.getSession().setAttribute("message", "账号或密码错误");
//            request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
            response.sendRedirect(request.getContextPath()+"/login.jsp");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
