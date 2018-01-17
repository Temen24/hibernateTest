package dao;

import entity.Contacts;

import java.util.List;

public interface ContactsDAO {

    void add(Contacts contacts);

    List<Contacts> getAll();
    Contacts getById(int id);

    void update(Contacts contacts);

    void delete(Contacts contacts);

}
