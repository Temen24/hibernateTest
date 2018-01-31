package service;

import business_logic.SessionUtil;
import dao.UserDAO;
import entity.User;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class UserService extends SessionUtil implements UserDAO {


    @Override
    public void add(User user) {
        openTransactionSession();
        Session session = getSession();
        session.save(user);
        closeTransactionSesstion();
    }

    @Override
    public List<User> getAll() {
        openTransactionSession();

        String sql = "SELECT * FROM USER";

        Session session = getSession();
        Query query = session.createNativeQuery(sql).addEntity(User.class);
        List<User> userList = query.list();

        closeTransactionSesstion();

        return userList;
    }

    @Override
    public User getById(int id) {
        openTransactionSession();

        String sql = "SELECT * FROM USER WHERE ID=:id";

        Session session = getSession();
        Query query = session.createNativeQuery(sql).addEntity(User.class);
        User user = (User) query.getSingleResult();

        closeTransactionSesstion();

        return user;
    }

    @Override
    public void update(User user) {
        openTransactionSession();
        Session session = getSession();
        session.update(user);
        closeTransactionSesstion();
    }

    @Override
    public void delete(User user) {
        openTransactionSession();
        Session session = getSession();
        session.remove(user);
        closeTransactionSesstion();
    }
}
