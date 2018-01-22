package dao;

import entity.Contact;

import java.sql.SQLException;
import java.util.List;

public interface ContactDAO {

    void add(Contact contact) throws SQLException;

    List<Contact> getAll() throws SQLException;
    Contact getById(int id) throws SQLException;

    void update(Contact contact) throws SQLException;

    void delete(Contact contact) throws SQLException;

}
