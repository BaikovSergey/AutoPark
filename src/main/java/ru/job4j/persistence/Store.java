package ru.job4j.persistence;

import org.hibernate.SessionFactory;
import ru.job4j.domain.CarPhoto;
import ru.job4j.domain.SellOrder;
import ru.job4j.domain.User;

import java.util.Collection;

public interface Store {
    SellOrder createSellOrder(SellOrder sellOrder, SessionFactory sf);
    User createUser(User user, SessionFactory sf);
    CarPhoto createCarPhoto(CarPhoto photo, SessionFactory sf);
    void updateSellOrder(SellOrder sellOrder, SessionFactory sf);
    void updateUser(User user, SessionFactory sf);
    void updateCarPhoto(CarPhoto photo, SessionFactory sf);
    void deleteSellOrder(Integer id, SessionFactory sf);
    void deleteUser(Integer id, SessionFactory sf);
    void deleteCarPhoto(Integer id, SessionFactory sf);
    Collection<SellOrder> findAllSellOrders(SessionFactory sf);
    Collection<User> findAllUsers(SessionFactory sf);
    Collection<CarPhoto> findAllCarPhotos(SessionFactory sf);
    SellOrder findSellOrderById(Integer id, SessionFactory sf);
    User findUserById(Integer id, SessionFactory sf);
    User findUserByEmail(String email, SessionFactory sf);
    CarPhoto findCarPhotoById (Integer id, SessionFactory sf);
}
