package service;

import business_logic.Util;
import dao.ProjectDAO;
import entity.Project;
import logger.CustomLog;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProjectService extends Util implements ProjectDAO {

    private Connection connection = getConnection();

    @Override
    public void add(Project project) {
        PreparedStatement ps = null;
        String sql = "INSERT INTO PROJECT (ID, TITLE) VALUES (?, ?)";
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, project.getId());
            ps.setString(2, project.getTitle());
            ps.executeUpdate();
        } catch (SQLException e) {
            CustomLog.log("Add project error", e);
        } finally {
            closeConnections(ps, connection);
        }
    }

    @Override
    public List<Project> getAll() {
        List<Project> projectList = new ArrayList<>();
        String sql = "SELECT ID, TITLE FROM PROJECT";
        Statement statement = null;

        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Project project = new Project();
                project.setId(resultSet.getInt("ID"));
                project.setTitle(resultSet.getString("TITLE"));
                projectList.add(project);
            }
        } catch (SQLException e) {
            CustomLog.log("Get all projects error", e);
        } finally {
            closeConnections(statement, connection);
        }
        return projectList;
    }

    @Override
    public Project getById(int id) {
        Project project = new Project();
        String sql = "SELECT ID, TITLE FROM PROJECT WHERE ID=?";
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet resultSet = ps.executeQuery();

            project.setId(resultSet.getInt("ID"));
            project.setTitle((resultSet.getString("TITLE")));
        } catch (SQLException e) {
            CustomLog.log("Get project error", e);
        } finally {
            closeConnections(ps, connection);
        }
        return project;
    }

    @Override
    public void update(Project project) {
        PreparedStatement ps = null;
        String sql = "UPDATE PROJECT SET TITLE=? WHERE ID=?";
        try {
            ps = connection.prepareStatement(sql);

            ps.setString(1, project.getTitle());
            ps.setInt(2, project.getId());

            ps.executeUpdate();
        } catch (SQLException e) {
            CustomLog.log("Update project error", e);
        } finally {
            closeConnections(ps, connection);
        }
    }

    @Override
    public void delete(Project project) {
        PreparedStatement ps = null;
        String sql = "DELETE FROM PROJECT WHERE ID=?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, project.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            CustomLog.log("Delete project error", e);
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
