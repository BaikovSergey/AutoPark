package ru.job4j.persistence;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import ru.job4j.domain.CarPhoto;
import ru.job4j.domain.SellOrder;
import ru.job4j.domain.User;

import java.util.Collection;
import java.util.function.Consumer;
import java.util.function.Function;

public class PsqlStore implements Store {

    private static final class Lazy {
        private static final Store INST = new PsqlStore();
    }

    public static Store instOf() {
        return Lazy.INST;
    }

    @Override
    public SellOrder createSellOrder(SellOrder sellOrder, SessionFactory sf) {
        this.transaction(session -> session.save(sellOrder), sf);
        return sellOrder;
    }

    @Override
    public User createUser(User user, SessionFactory sf) {
        this.transaction(session -> session.save(user), sf);
        return user;
    }

    @Override
    public CarPhoto createCarPhoto(CarPhoto photo, SessionFactory sf) {
        this.transaction(session -> session.save(photo), sf);
        return photo;
    }

    @Override
    public void updateSellOrder(SellOrder sellOrder, SessionFactory sf) {
        this.transactionVoid(session -> session.update(sellOrder), sf);
    }

    @Override
    public void updateUser(User user, SessionFactory sf) {
        this.transactionVoid(session -> session.update(user), sf);
    }

    @Override
    public void updateCarPhoto(CarPhoto photo, SessionFactory sf) {
        this.transactionVoid(session -> session.update(photo), sf);
    }

    @Override
    public void deleteSellOrder(Integer id, SessionFactory sf) {
        SellOrder sellOrder = new SellOrder();
        sellOrder.setId(id);
        this.transactionVoid(session -> session.delete(sellOrder), sf);
    }

    @Override
    public void deleteUser(Integer id, SessionFactory sf) {
        User user = new User();
        user.setId(id);
        this.transactionVoid(session -> session.delete(user), sf);
    }

    @Override
    public void deleteCarPhoto(Integer id, SessionFactory sf) {
        CarPhoto photo = new CarPhoto();
        photo.setId(id);
        this.transactionVoid(session -> session.delete(photo), sf);
    }

    @Override
    public Collection<SellOrder> findAllSellOrders(SessionFactory sf) {
        return this.transaction(session -> session.createQuery(
                "from ru.job4j.domain.SellOrder").list(), sf);
    }

    @Override
    public Collection<User> findAllUsers(SessionFactory sf) {
        return this.transaction(session -> session.createQuery(
                "from ru.job4j.domain.User").list(), sf);
    }

    @Override
    public Collection<CarPhoto> findAllCarPhotos(SessionFactory sf) {
        return this.transaction(session -> session.createQuery(
                "from ru.job4j.domain.CarPhoto").list(), sf);
    }

    @Override
    public SellOrder findSellOrderById(Integer id, SessionFactory sf) {
        return this.transaction(session -> session.get(SellOrder.class, id), sf);
    }

    @Override
    public User findUserById(Integer id, SessionFactory sf) {
        return this.transaction(session -> session.get(User.class, id), sf);
    }

    @Override
    public User findUserByEmail(String email, SessionFactory sf) {
        Session session = sf.openSession();
        Query<User> query = session.createQuery("from User u where u.email=:scn",
                User.class);
        query.setParameter("scn", email);
        return query.uniqueResult();
    }

    @Override
    public CarPhoto findCarPhotoById(Integer id, SessionFactory sf) {
        return this.transaction(session -> session.get(CarPhoto.class, id), sf);
    }

    private <T> T transaction(final Function<Session, T> command, SessionFactory sf) {
        final Session session = sf.openSession();
        final Transaction tx = session.beginTransaction();
        try {
            T result = command.apply(session);
            tx.commit();
            return result;
        } catch (final Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    private void transactionVoid(final Consumer<Session> command, SessionFactory sf) {
        final Session session = sf.openSession();
        final Transaction tx = session.beginTransaction();
        try {
            command.accept(session);
            tx.commit();
        } catch (final Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
    }
}
