package ru.job4j.presentation;

import ru.job4j.application.AutoPark;
import ru.job4j.domain.CarPhoto;
import ru.job4j.domain.SellOrder;
import ru.job4j.domain.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PostNewSellOrder extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        AutoPark.instOf().createSellOrder(formSellOrder(req));
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }

    private SellOrder formSellOrder(HttpServletRequest req) {
        SellOrder result = new SellOrder();
        int userId = Integer.parseInt(req.getParameter("userId"));
        User user = AutoPark.instOf().findUserById(userId);
        result.setBrand(req.getParameter("brand"));
        result.setModel(req.getParameter("model"));
        result.setBody(req.getParameter("body"));
        result.setCondition(req.getParameter("condition"));
        result.setEngineVolume(Double.parseDouble(req.getParameter("engineVolume")));
        result.setEngine(req.getParameter("engine"));
        result.setDrive(req.getParameter("drive"));
        result.setTransmission(req.getParameter("transmission"));
        result.setMileage(Integer.parseInt(req.getParameter("mileage")));
        result.setPrice(Integer.parseInt(req.getParameter("price")));
        result.setUser(user);
        return result;
    }
}
