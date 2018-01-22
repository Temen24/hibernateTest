package dao;

import entity.Contacts;

import java.sql.SQLException;
import java.util.List;

public interface ContactsDAO {

    void add(Contacts contacts) throws SQLException;

    List<Contacts> getAll() throws SQLException;
    Contacts getById(int id) throws SQLException;

    void update(Contacts contacts) throws SQLException;

    void delete(Contacts contacts) throws SQLException;

}
