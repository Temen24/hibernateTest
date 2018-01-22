package dao;

import entity.Projects;

import java.sql.SQLException;
import java.util.List;

public interface ProjectsDAO {

    void add(Projects projects) throws SQLException;

    List<Projects> getAll();
    Projects getById(int id);

    void update(Projects projects);

    void delete(Projects projects);
}
