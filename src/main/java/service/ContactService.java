package service;

import business_logic.SessionUtil;
import dao.ContactDAO;
import entity.Contact;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class ContactService extends SessionUtil implements ContactDAO {

    @Override
    public void add(Contact contact) {
        openTransactionSession();
        Session session = getSession();
        session.save(contact);
        closeTransactionSesstion();
    }

    @Override
    public List<Contact> getAll() {
        openTransactionSession();

        String sql = "SELECT * FROM CONTACT";

        Session session = getSession();
        Query query = session.createNativeQuery(sql).addEntity(Contact.class);
        List<Contact> contactList = query.list();

        closeTransactionSesstion();

        return contactList;
    }

    @Override
    public Contact getById(int id) {
        openTransactionSession();

        String sql = "SELECT * FROM CONTACT WHERE ID = :id";

        Session session = getSession();
        Query query = session.createNativeQuery(sql).addEntity(Contact.class);
        query.setParameter("id", id);

        Contact contact = (Contact) query.getSingleResult();

        closeTransactionSesstion();
        return contact;
    }

    @Override
    public void update(Contact contact) {
        openTransactionSession();

        Session session = getSession();
        session.update(contact);

        closeTransactionSesstion();
    }

    @Override
    public void delete(Contact contact) {
        openTransactionSession();

        Session session = getSession();
        session.remove(contact);

        closeTransactionSesstion();
    }
}
