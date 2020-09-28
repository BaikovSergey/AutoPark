package ru.job4j.application;

import org.hibernate.SessionFactory;
import ru.job4j.domain.SellOrder;
import ru.job4j.domain.User;

import java.util.Collection;

public interface SellPlatform {
    SellOrder createSellOrder(SellOrder sellOrder);
    User createUser(User user);
    void updateSellOrder(SellOrder sellOrder);
    void updateUser(User user);
    void deleteSellOrder(Integer id);
    void deleteUser(Integer id);
    Collection<SellOrder> findAllSellOrders();
    Collection<User> findAllUsers();
    SellOrder findSellOrderById(Integer id);
    User findUserById(Integer id);
    User findUserByEmail(String email);
}
