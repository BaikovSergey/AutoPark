package ru.job4j.application;

import org.hibernate.SessionFactory;
import ru.job4j.domain.CarPhoto;
import ru.job4j.domain.SellOrder;
import ru.job4j.domain.User;

import java.util.Collection;

public interface SellPlatform {
    SellOrder createSellOrder(SellOrder sellOrder);
    User createUser(User user);
    CarPhoto createCarPhoto(CarPhoto photo);
    void updateSellOrder(SellOrder sellOrder);
    void updateUser(User user);
    void updateCarPhoto(CarPhoto photo);
    void deleteSellOrder(Integer id);
    void deleteUser(Integer id);
    void deleteCarPhoto(Integer id);
    Collection<SellOrder> findAllSellOrders();
    Collection<User> findAllUsers();
    Collection<CarPhoto> findAllCarPhotos();
    SellOrder findSellOrderById(Integer id);
    User findUserById(Integer id);
    User findUserByEmail(String email);
    CarPhoto findCarPhotoById (Integer id);
}
