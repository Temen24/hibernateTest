package service;

import business_logic.Util;
import dao.UserDAO;
import entity.User;
import logger.CustomLog;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserService extends Util implements UserDAO {

    Connection connection = getConnection();

    @Override
    public void add(User user) {
        PreparedStatement ps = null;
        String sql = "INSERT INTO USER (ID, NAME, OS, BIRTHDAY) VALUES (?, ?, ?, ?)";
        try {
            ps = connection.prepareStatement(sql);

            ps.setInt(1, user.getId());
            ps.setString(2, user.getName());
            ps.setString(3, user.getOs());
            ps.setDate(4, user.getBirthday());

            ps.executeUpdate();
        } catch (SQLException e) {
            CustomLog.log("Add user error", e);
            e.printStackTrace();
        } finally {
            closeConnections(ps, connection);
        }
    }

    @Override
    public List<User> getAll() {
        List<User> userList = new ArrayList<>();
        String sql = "SELECT ID, NAME, OS, BIRTHDAY FROM USER";
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    @Override
    public User getById(int id) {
        return null;
    }

    @Override
    public void update(User user) {

    }

    @Override
    public void delete(User user) {

    }

    public void closeConnections(PreparedStatement ps, Connection connection) {
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void closeConnections(Statement statement, Connection connection) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
