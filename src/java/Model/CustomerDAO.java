/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Entity.Customer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author HP DAO: database object access
 */
public class CustomerDAO {
    // DBConnection db;

    Connection conn;

    public CustomerDAO(DBConnection db) {
        conn = db.getConnection();
    }

    public int insertCustomer(Customer cus) {
        int n = 0;
        String sql = "INSERT INTO Customer(cname,phone,email,username,password) VALUES(?,?,?,?,?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, cus.getName());
            pre.setString(2, cus.getPhoneNumber());
            pre.setString(3, cus.getEmail());
            pre.setString(4, cus.getUsername());
            pre.setString(5, cus.getPassword());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return n;
    }

    public int updateCustomerClient(String cname, String phone, String email) {
        int n = 0;
        String sql = "UPDATE Customer SET cname=?, phone=?, email=?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, cname);
            pre.setString(2, phone);
            pre.setString(3, email);
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int changePassword(int id, String oldpassWord, String newPassword) {
        int n = 0;
        String checkSQL = "SELECT * from Customer where cid= ? and password = ?";

        PreparedStatement statement;
        try {
            statement = conn.prepareStatement(checkSQL);
            statement.setInt(1, id);
            statement.setString(2, oldpassWord);
            ResultSet rs = statement.executeQuery();
            if (!rs.next()) {
                return n;
            }

        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        String sql = "UPDATE CUSTOMER SET password = ? WHERE cid= ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, newPassword);
            pre.setInt(2, id);
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return n;
    }

    public int changeStatus(int id) {
        int n = 0;
        String checkSQL = "SELECT * from Customer where cid= ?";
        int status = 0;
        PreparedStatement statement;
        try {
            statement = conn.prepareStatement(checkSQL);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (!rs.next()) {
                return n;
            }
            status = rs.getInt(7);
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        String sql = "UPDATE CUSTOMER SET status = ? WHERE cid= ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, (status == 0) ? 1 : 0);
            pre.setInt(2, id);
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return n;
    }

    public int deleteCustomer(int id) {
        int n = 0;
        //check foreign key: select id in Bill
        String checkSQL = "SELECT bid from Bill where cid= ?";
        PreparedStatement statement;
        try {
            statement = conn.prepareStatement(checkSQL);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return n;
            }

        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        String sql = "DELETE FROM Customer WHERE cid=" + id;
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return n;
    }

    public int resetPassword(int id) {
        int n = 0;
        String checkSQL = "SELECT * from Customer where cid= ?";

        PreparedStatement statement;
        try {
            statement = conn.prepareStatement(checkSQL);
            statement.setInt(1, id);          
            ResultSet rs = statement.executeQuery();
            if (!rs.next()) {
                return n;
            }

        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        String sql = "UPDATE CUSTOMER SET password = ? WHERE cid= ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, "123@abc");
            pre.setInt(2, id);
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return n;
    }
//    public static void main(String[] args) {
//        DBConnection DB = new DBConnection();
//        CustomerDAO D = new CustomerDAO(DB);
//        D.deleteCustomer(1);
//    }
}
