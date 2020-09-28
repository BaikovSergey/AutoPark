package ru.job4j.application;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.job4j.domain.SellOrder;
import ru.job4j.domain.User;
import ru.job4j.persistence.PsqlStore;

import java.util.Collection;

public class AutoPark implements SellPlatform {

    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();

    private SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();

    private static final class Lazy {
        private static final AutoPark INST = new AutoPark();
    }

    public static AutoPark instOf() {
        return Lazy.INST;
    }

    @Override
    public SellOrder createSellOrder(SellOrder sellOrder) {
        return PsqlStore.instOf().createSellOrder(sellOrder, sf);
    }

    @Override
    public User createUser(User user) {
        return PsqlStore.instOf().createUser(user, sf);
    }

    @Override
    public void updateSellOrder(SellOrder sellOrder) {
        PsqlStore.instOf().updateSellOrder(sellOrder, sf);
    }

    @Override
    public void updateUser(User user) {
        PsqlStore.instOf().updateUser(user, sf);
    }

    @Override
    public void deleteSellOrder(Integer id) {
        PsqlStore.instOf().deleteSellOrder(id, sf);
    }

    @Override
    public void deleteUser(Integer id) {
        PsqlStore.instOf().deleteUser(id, sf);
    }

    @Override
    public Collection<SellOrder> findAllSellOrders() {
        return PsqlStore.instOf().findAllSellOrders(sf);
    }

    @Override
    public Collection<User> findAllUsers() {
        return PsqlStore.instOf().findAllUsers(sf);
    }

    @Override
    public SellOrder findSellOrderById(Integer id) {
        return PsqlStore.instOf().findSellOrderById(id, sf);
    }

    @Override
    public User findUserById(Integer id) {
        return PsqlStore.instOf().findUserById(id, sf);
    }

    @Override
    public User findUserByEmail(String email) {
        return PsqlStore.instOf().findUserByEmail(email, sf);
    }
}
