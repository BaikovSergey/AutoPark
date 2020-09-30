package ru.job4j.presentation;

import ru.job4j.application.AutoPark;
import ru.job4j.domain.SellOrder;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ChangeSellOrderStatus extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer orderId = Integer.parseInt(req.getParameter("orderId"));
        SellOrder sellOrder = AutoPark.instOf().findSellOrderById(orderId);
        sellOrder.setStatus(true);
        AutoPark.instOf().updateSellOrder(sellOrder);
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }
}
