package service;

import business_logic.Util;
import dao.UserDAO;
import entity.User;
import logger.CustomLog;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserService extends Util implements UserDAO {

    private Connection connection = getConnection();

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
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("ID"));
                user.setName(resultSet.getString("NAME"));
                user.setOs(resultSet.getString("OS"));
                user.setBirthday(resultSet.getDate("BIRTHDAY"));
                userList.add(user);
            }
        } catch (SQLException e) {
            CustomLog.log("Get all users error", e);
        } finally {
            closeConnections(statement, connection);
        }
        return userList;
    }

    @Override
    public User getById(int id) {
        PreparedStatement ps = null;
        String sql = "SELECT ID, NAME, OS, BIRTHDAY FROM USER WHERE ID=?";
        User user = new User();
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet resultSet = ps.executeQuery();

            user.setId(resultSet.getInt("ID"));
            user.setName(resultSet.getString("NAME"));
            user.setOs(resultSet.getString("OS"));
            user.setBirthday(resultSet.getDate("BIRTHDAY"));
        } catch (SQLException e) {
            CustomLog.log("Get user error", e);
        } finally {
            closeConnections(ps, connection);
        }
        return user;
    }

    @Override
    public void update(User user) {
        PreparedStatement ps = null;
        String sql = "UPDATE USER SET NAME=?, OS=?, BIRTHDAY=? WHERE ID=?";
        try {
            ps = connection.prepareStatement(sql);

            ps.setString(1, user.getName());
            ps.setString(2, user.getOs());
            ps.setDate(3, user.getBirthday());
            ps.setInt(4, user.getId());

            ps.executeUpdate();
        } catch (SQLException e) {
            CustomLog.log("Update user error", e);
        } finally {
            closeConnections(ps, connection);
        }
    }

    @Override
    public void delete(User user) {
        PreparedStatement ps = null;
        String sql = "DELETE FROM USER WHERE ID=?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, user.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            CustomLog.log("Delete user error", e);
        } finally {
            closeConnections(ps, connection);
        }
    }

    private void closeConnections(PreparedStatement ps, Connection connection) {
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

    private void closeConnections(Statement statement, Connection connection) {
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
