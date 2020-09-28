package ru.job4j.presentation;

import ru.job4j.application.AutoPark;
import ru.job4j.domain.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class Login extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        User user = AutoPark.instOf().findUserByEmail(email);
        if (user != null && (user.getEmail().equals(email) && user.getPassword().equals(password))) {
            HttpSession sc = req.getSession();
            sc.setAttribute("user", user);
            sc.setAttribute("userId", user.getId());
            resp.sendRedirect(req.getContextPath());
        } else {
            req.setAttribute("error", "Error: Incorrect email or password");
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }
    }
}
