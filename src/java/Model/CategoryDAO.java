/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Entity.Category;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kudo
 */
public class CategoryDAO {

    Connection conn;

    public CategoryDAO(DBConnection db) {
        conn = db.getConnection();
    }

    public int insertCategory(Category category) {
        int n = 0;
        String sql = "INSERT INTO [category]\n"
                + "([catID]\n"
                + ",[catName]\n"
                + ",[status])\n"
                + "VALUES\n"
                + " (?,?,?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, category.getID());
            pre.setString(2, category.getName());
            pre.setInt(3, category.getStatus());

            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return n;
    }

    public int updateCategory(String ID, String name) {
        int n = 0;
        String sql = "UPDATE category SET catName=? WHERE catID=?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, name);
            pre.setString(2, ID);
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
 public int changeStatus(String ID) {
        int n = 0;
        String checkSQL = "SELECT * from Category where catid= ?";
        int status = 0;
        PreparedStatement statement;
        try {
            statement = conn.prepareStatement(checkSQL);
            statement.setString(1, ID);
            ResultSet rs = statement.executeQuery();
            if (!rs.next()) {
                return n;
            }
            status = rs.getInt(3);
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        String sql = "UPDATE Category SET status = ? WHERE catid= ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, (status == 0) ? 1 : 0);
            pre.setString(2, ID);
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return n;
    }
    public int deleteBill(int id) {
        int n = 0;
        //check foreign key: select id in Bill
        String checkSQL = "SELECT * from Product where catid= ?";
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

        String sql = "DELETE FROM category WHERE catid=" + id;
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
}
