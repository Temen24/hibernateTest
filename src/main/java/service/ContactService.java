package service;

import business_logic.Util;
import dao.ContactDAO;
import entity.Contact;
import logger.CustomLog;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContactService extends Util implements ContactDAO {

    private Connection connection = getConnection();

    @Override
    public void add(Contact contact) {
        PreparedStatement ps = null;
        String sql = "INSERT INTO CONTACT (ID, PHONE, VK_URL) VALUES (?, ?, ?)";
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, contact.getId());
            ps.setString(2, contact.getPhone());
            ps.setString(3, contact.getVkUrl());
            ps.executeUpdate();
        } catch (SQLException e) {
            CustomLog.log("Add contact error", e);
        } finally {
            closeConnections(ps, connection);
        }
    }

    @Override
    public List<Contact> getAll() {
        List<Contact> contactList = new ArrayList<>();
        String sql = "SELECT ID, PHONE, VK_URL FROM CONTACT";
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Contact contact = new Contact();
                contact.setId(resultSet.getInt("ID"));
                contact.setPhone(resultSet.getString("PHONE"));
                contact.setVkUrl(resultSet.getString("VK_URL"));
                contactList.add(contact);
            }
        } catch (SQLException e) {
            CustomLog.log("Get all contacts error", e);
        } finally {
            closeConnections(statement, connection);
        }
        return contactList;
    }

    @Override
    public Contact getById(int id) {
        PreparedStatement ps = null;
        String sql = "SELECT ID, PHONE, VK_URL FROM CONTACT WHERE ID=?";
        Contact contact = new Contact();
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet resultSet = ps.executeQuery();

            contact.setId(resultSet.getInt("ID"));
            contact.setPhone(resultSet.getString("PHONE"));
            contact.setVkUrl(resultSet.getString("VK_URL"));
        } catch (SQLException e) {
            CustomLog.log("Get contact error", e);
        } finally {
            closeConnections(ps, connection);
        }
        return contact;
    }

    @Override
    public void update(Contact contact) {
        PreparedStatement ps = null;
        String sql = "UPDATE CONTACT SET PHONE=?, VK_URL=? WHERE ID=?";
        try {
            ps = connection.prepareStatement(sql);

            ps.setString(1, contact.getPhone());
            ps.setString(2, contact.getVkUrl());
            ps.setInt(3, contact.getId());

            ps.executeUpdate();
        } catch (SQLException e) {
            CustomLog.log("Update contacts error", e);
        } finally {
            closeConnections(ps, connection);
        }
    }

    @Override
    public void delete(Contact contact) {
        PreparedStatement ps = null;
        String sql = "DELETE FROM CONTACT WHERE ID=?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, contact.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            CustomLog.log("Delete contact error", e);
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
