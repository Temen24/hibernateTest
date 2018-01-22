package dao;

import entity.User;

import java.util.List;

public interface UserDAO {
    void add(User user);

    List<User> getAll();
    User getById(int id);

    void update(User user);

    void delete(User user);
}
