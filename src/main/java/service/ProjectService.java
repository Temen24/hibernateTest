package service;

import business_logic.SessionUtil;
import dao.ProjectDAO;
import entity.Project;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class ProjectService extends SessionUtil implements ProjectDAO {

    @Override
    public void add(Project project) {
        openTransactionSession();
        Session session = getSession();
        session.save(project);
        closeTransactionSesstion();
    }

    @Override
    public List<Project> getAll() {
        openTransactionSession();

        String sql = "SELECT * FROM PROJECT";

        Session session = getSession();
        Query query = session.createNativeQuery(sql).addEntity(Project.class);
        List<Project> projectList = query.list();

        closeTransactionSesstion();

        return projectList;
    }

    @Override
    public Project getById(int id) {
        openTransactionSession();

        String sql = "SELECT * FROM PROJECT WHERE ID=:id";
        Session session = getSession();
        Query query = session.createNativeQuery(sql).addEntity(Project.class);
        Project project = (Project) query.getSingleResult();

        closeTransactionSesstion();

        return project;
    }

    @Override
    public void update(Project project) {
        openTransactionSession();

        Session session = getSession();
        session.update(project);

        closeTransactionSesstion();
    }

    @Override
    public void delete(Project project) {
        openTransactionSession();

        Session session = getSession();
        session.remove(project);

        closeTransactionSesstion();
    }
}
