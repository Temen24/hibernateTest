package service;

import business_logic.Util;
import dao.ContactDAO;
import entity.Contact;
import logger.CustomLog;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContactService extends Util implements ContactDAO {

    Connection connection = getConnection();

    @Override
    public void add(Contact contact) throws SQLException {
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
    public List<Contact> getAll() throws SQLException {
        List<Contact> contactList = new ArrayList<>();
        String sql = "SELECT ID, PHONE, VK_URL FROM CONTACT";
        Statement statement = null;
        try{
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                Contact contact = new Contact();
                contact.setId(resultSet.getInt("ID"));
                contact.setPhone(resultSet.getString("PHONE"));
                contact.setVkUrl(resultSet.getString("VK_URL"));
                contactList.add(contact);
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
        return contactList;
    }

    @Override
    public Contact getById(int id) throws SQLException {
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
    public void update(Contact contact) throws SQLException {
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
    public void delete(Contact contact) throws SQLException {
        PreparedStatement ps = null;
        String sql = "DELETE FROM CONTACT WHERE ID=?";
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
