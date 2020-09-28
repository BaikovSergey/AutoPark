package ru.job4j.presentation;

import ru.job4j.application.AutoPark;
import ru.job4j.domain.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Registration extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("reg.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        AutoPark.instOf().createUser(
                new User(
                        req.getParameter("name"),
                        req.getParameter("email"),
                        req.getParameter("password")
                )
        );
        req.getRequestDispatcher("login.jsp").forward(req, resp);
    }
}
