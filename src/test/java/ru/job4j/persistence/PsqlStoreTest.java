package ru.job4j.persistence;

import org.junit.Test;
import ru.job4j.application.AutoPark;
import ru.job4j.domain.SellOrder;
import ru.job4j.domain.User;

import java.util.Collection;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class PsqlStoreTest {

    @Test
    public void createSellOrder() {
        User user = generateUser();
        SellOrder sellOrder = generateOrder();
        int userId = AutoPark.instOf().createUser(user).getId();
        int orderId = AutoPark.instOf().createSellOrder(sellOrder).getId();

        SellOrder result = AutoPark.instOf().findSellOrderById(orderId);
        result.setUser(user);
        SellOrder expected = generateOrder();
        expected.setId(orderId);
        expected.setUser(user);

        AutoPark.instOf().deleteSellOrder(orderId);
        AutoPark.instOf().deleteUser(userId);

        assertThat(result, is(expected));
    }

    @Test
    public void createUser() {
        User user = generateUser();
        int userId = AutoPark.instOf().createUser(user).getId();

        User result = AutoPark.instOf().findUserById(userId);

        AutoPark.instOf().deleteUser(userId);

        assertThat(result.getName(), is("Test User"));
    }

    @Test
    public void updateSellOrder() {
        User user = generateUser();
        SellOrder sellOrder = generateOrder();
        int userId = AutoPark.instOf().createUser(user).getId();
        int orderId = AutoPark.instOf().createSellOrder(sellOrder).getId();

        sellOrder.setBrand("Mercedes");
        AutoPark.instOf().updateSellOrder(sellOrder);
        SellOrder result = AutoPark.instOf().findSellOrderById(orderId);

        AutoPark.instOf().deleteSellOrder(orderId);
        AutoPark.instOf().deleteUser(userId);

        assertThat(result.getBrand(), is("Mercedes"));
    }

    @Test
    public void updateUser() {
        User user = generateUser();
        int userId = AutoPark.instOf().createUser(user).getId();

        user.setName("Changed");
        AutoPark.instOf().updateUser(user);
        User result = AutoPark.instOf().findUserById(userId);

        AutoPark.instOf().deleteUser(userId);

        assertThat(result.getName(), is("Changed"));
    }

    @Test
    public void deleteSellOrder() {
        User user = generateUser();
        SellOrder sellOrder = generateOrder();
        int userId = AutoPark.instOf().createUser(user).getId();
        int orderId = AutoPark.instOf().createSellOrder(sellOrder).getId();

        AutoPark.instOf().deleteSellOrder(orderId);
        AutoPark.instOf().deleteUser(userId);

        Collection<SellOrder> result = AutoPark.instOf().findAllSellOrders();

        assertThat(result.size(), is(0));
    }

    @Test
    public void deleteUser() {
        User user = generateUser();
        int userId = AutoPark.instOf().createUser(user).getId();

        AutoPark.instOf().deleteUser(userId);

        Collection<User> result = AutoPark.instOf().findAllUsers();

        assertThat(result.size(), is(0));
    }

    @Test
    public void findAllSellOrders() {
        User user = generateUser();
        SellOrder sellOrder = generateOrder();
        int userId = AutoPark.instOf().createUser(user).getId();
        int orderId = AutoPark.instOf().createSellOrder(sellOrder).getId();

        Collection<SellOrder> result = AutoPark.instOf().findAllSellOrders();

        AutoPark.instOf().deleteSellOrder(orderId);
        AutoPark.instOf().deleteUser(userId);

        assertThat(result.size(), is(1));
    }

    @Test
    public void findAllUsers() {
        User user = generateUser();
        int userId = AutoPark.instOf().createUser(user).getId();

        Collection<User> result = AutoPark.instOf().findAllUsers();

        AutoPark.instOf().deleteUser(userId);

        assertThat(result.size(), is(1));
    }

    @Test
    public void findSellOrderById() {
        User user = generateUser();
        SellOrder sellOrder = generateOrder();
        int userId = AutoPark.instOf().createUser(user).getId();
        int orderId = AutoPark.instOf().createSellOrder(sellOrder).getId();

        Collection<SellOrder> result = AutoPark.instOf().findAllSellOrders();

        AutoPark.instOf().deleteSellOrder(orderId);
        AutoPark.instOf().deleteUser(userId);

        assertThat(result.iterator().next().getBrand(), is("BMW"));
    }

    @Test
    public void findUserById() {
        User user = generateUser();
        int userId = AutoPark.instOf().createUser(user).getId();

        Collection<User> result = AutoPark.instOf().findAllUsers();

        AutoPark.instOf().deleteUser(userId);

        assertThat(result.iterator().next().getName(), is("Test User"));
    }

    @Test
    public void findUserByEmail() {
        User user = generateUser();
        int userId = AutoPark.instOf().createUser(user).getId();
        String email = "Test@mail.com";

        User result = AutoPark.instOf().findUserByEmail(email);

        AutoPark.instOf().deleteUser(userId);

        assertThat(result.getName(), is("Test User"));
    }

    private User generateUser() {
        return new User("Test User", "Test@mail.com", "Password");
    }

    private SellOrder generateOrder() {
        return new SellOrder(
                "new",
                "BMW",
                "X5",
                "Crossover",
                "5-speed automatic",
                "4WD",
                "Diesel",
                1,
                3.0d,
                65000L
        );
    }
}