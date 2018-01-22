package service;

import business_logic.Util;
import dao.ContactsDAO;
import entity.Contacts;
import logger.CustomLog;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContactsService extends Util implements ContactsDAO {

    Connection connection = getConnection();

    @Override
    public void add(Contacts contacts) throws SQLException {
        PreparedStatement ps = null;
        String sql = "INSERT INTO CONTACTS (ID, PHONE, VK_URL) VALUES (?, ?, ?)";
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, contacts.getId());
            ps.setString(2, contacts.getPhone());
            ps.setString(3, contacts.getVkUrl());
            ps.executeUpdate();
        } catch (SQLException e) {
            CustomLog.log("Add contact error", e);
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
    public List<Contacts> getAll() throws SQLException {
        List<Contacts> contactsList = new ArrayList<>();
        String sql = "SELECT ID, PHONE, VK_URL FROM CONTACTS";
        Statement statement = null;
        try{
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                Contacts contact = new Contacts();
                contact.setId(resultSet.getInt("ID"));
                contact.setPhone(resultSet.getString("PHONE"));
                contact.setVkUrl(resultSet.getString("VK_URL"));
                contactsList.add(contact);
            }
        } catch (SQLException e) {
            CustomLog.log("Get all contacts error", e);
            e.printStackTrace();
        } finally {
            if(statement != null) {
                statement.close();
            }
            if (connection != null){
                connection.close();
            }
        }
        return contactsList;
    }

    @Override
    public Contacts getById(int id) throws SQLException {
        PreparedStatement ps = null;
        String sql = "SELECT ID, PHONE, VK_URL FROM CONTACTS WHERE ID=?";
        Contacts contact = new Contacts();
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet resultSet = ps.executeQuery();

            contact.setId(resultSet.getInt("ID"));
            contact.setPhone(resultSet.getString("PHONE"));
            contact.setVkUrl(resultSet.getString("VK_URL"));

//            ps.executeUpdate();
        } catch (SQLException e) {
            CustomLog.log("Get all contacts error", e);
            e.printStackTrace();
        } finally {
            if(ps != null) {
                ps.close();
            }
            if (connection != null){
                connection.close();
            }
        }
        return contact;
    }

    @Override
    public void update(Contacts contact) throws SQLException {
        PreparedStatement ps = null;
        String sql = "UPDATE CONTACTS SET PHONE=?, VK_URL=? WHERE ID=?";
        try {
            ps = connection.prepareStatement(sql);

            ps.setString(1, contact.getPhone());
            ps.setString(2, contact.getVkUrl());
            ps.setInt(3, contact.getId());

            ps.executeUpdate();
        } catch (SQLException e) {
            CustomLog.log("Update contacts error", e);
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
    public void delete(Contacts contact) throws SQLException {
        PreparedStatement ps = null;
        String sql = "DELETE FROM CONTACTS WHERE ID=?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, contact.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            CustomLog.log("Update contacts error", e);
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
}
