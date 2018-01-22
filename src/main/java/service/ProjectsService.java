package service;

import business_logic.Util;
import dao.ProjectsDAO;
import entity.Projects;
import logger.CustomLog;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class ProjectsService extends Util implements ProjectsDAO {

    Connection connection = getConnection();

    @Override
    public void add(Projects project) throws SQLException {
        PreparedStatement ps = null;
        String sql = "INSERT INTO PROJECTS (ID, TITLE) VALUES (?, ?)";
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, project.getId());
            ps.setString(2, project.getTitle());
            ps.executeUpdate();
        } catch (SQLException e) {
            CustomLog.log("Add project error", e);
            e.printStackTrace();
        } finally {
            if(ps != null) {
                ps.close();
            }
            if (connection != null){
                connection.close();
            }
        }
    }

    @Override
    public List<Projects> getAll() {
        return null;
    }

    @Override
    public Projects getById(int id) {
        return null;
    }

    @Override
    public void update(Projects projects) {

    }

    @Override
    public void delete(Projects projects) {

    }
}
