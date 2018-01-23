package service;

import business_logic.Util;
import dao.UserProjDAO;
import entity.UserProj;
import logger.CustomLog;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserProjService extends Util implements UserProjDAO {

    private Connection connection = getConnection();

    @Override
    public void add(UserProj userProj) {
        PreparedStatement ps = null;
        String sql = "INSERT INTO USER_PROJ (USER_ID, PROJ_ID) VALUES (?, ?)";
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, userProj.getUserID());
            ps.setInt(2, userProj.getProjID());
            ps.executeUpdate();
        } catch (SQLException e) {
            CustomLog.log("Add user_proj error", e);
        } finally {
            closeConnections(ps, connection);
        }
    }

    @Override
    public List<UserProj> getAll() {
        List<UserProj> userProjList = new ArrayList<>();
        String sql = "SELECT USER_ID, PROJ_ID FROM USER_PROJ";
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                UserProj userProj = new UserProj();
                userProj.setUserID(resultSet.getInt("USER_ID"));
                userProj.setProjID(resultSet.getInt("PROJ_ID"));
                userProjList.add(userProj);
            }
        } catch (SQLException e) {
            CustomLog.log("Get all user_proj error", e);
        } finally {
            closeConnections(statement, connection);
        }
        return userProjList;
    }

    @Override
    public UserProj getByUserIdAndProjectId(int userId, int projId) {
        PreparedStatement ps = null;
        UserProj userProj = new UserProj();
        String sql = "SELECT USER_ID, PROJ_ID FROM USER_PROJ WHERE USER_ID=? AND PROJ_ID=?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, userId);
            ps.setInt(2, projId);

            ResultSet resultSet = ps.executeQuery();

            userProj.setUserID(resultSet.getInt("USER_ID"));
            userProj.setProjID(resultSet.getInt("PROJ_ID"));
        } catch (SQLException e) {
            CustomLog.log("Get user_proj error", e);
        } finally {
            closeConnections(ps, connection);
        }
        return userProj;
    }

    @Override
    public void update(UserProj userProj) {
        PreparedStatement ps = null;
        String sql = "UPDATE USER_PROJ SET USER_ID=?, PROJ_ID=?";
        try {
            ps = connection.prepareStatement(sql);

            ps.setInt(1, userProj.getUserID());
            ps.setInt(2, userProj.getProjID());

            ps.executeUpdate();
        } catch (SQLException e) {
            CustomLog.log("Update user_proj error", e);
        } finally {
            closeConnections(ps, connection);
        }
    }

    @Override
    public void delete(UserProj userProj) {
        PreparedStatement ps = null;
        String sql = "DELETE FROM USER_PROJ WHERE  USER_ID=? AND PROJ_ID=?";
        try {
            ps = connection.prepareStatement(sql);

            ps.setInt(1, userProj.getUserID());
            ps.setInt(2, userProj.getProjID());

            ps.executeUpdate();
        } catch (SQLException e) {
            CustomLog.log("Remove from user_proj error", e);
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
