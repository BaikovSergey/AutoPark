package ru.job4j.presentation;

import ru.job4j.application.AutoPark;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UploadPage extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int orderId = Integer.parseInt(req.getParameter("orderId"));
        req.setAttribute("order", AutoPark.instOf().findSellOrderById(orderId));
        req.getRequestDispatcher("/upload.jsp").forward(req, resp);
    }
}
