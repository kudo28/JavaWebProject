/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Entity.Bill;
import Entity.Product;
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
public class ProductDAO {

    Connection conn;

    public ProductDAO(DBConnection db) {
        conn = db.getConnection();
    }

    public int insertProduct(Product product) {
        int n = 0;
        String sql = "INSERT INTO [Product]\n"
                + "([pname]\n"
                + ",[quantity]\n"
                + ",[price]\n"
                + ",[image]\n"
                + ",[description]\n"
                + ",[catid])\n"
                + "VALUES(?,?,?,?,?,?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, product.getName());
            pre.setInt(2, product.getQuantity());
            pre.setInt(3, product.getPrice());
            pre.setString(4, product.getImage());
            pre.setString(5, product.getDescription());
            pre.setString(6, product.getCat_ID());
         
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return n;
    }

    public int updateProduct(String pname, int quantity, int price, String image, String description, String CID,int pid) {
        int n = 0;
        String sql = "UPDATE Product SET pname=?, quantity=?, price=?, image = ? , description= ? , catid = ? where pid = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, pname);
            pre.setInt(2, quantity);
            pre.setInt(3, price);
            pre.setString(4, image);
            pre.setString(5, description);
            pre.setString(6, CID);
            pre.setInt(7, pid);
                n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int deleteCategory(int id) {
        int n = 0;
        //check foreign key: select id in Bill
        String checkSQL = "SELECT * from BillDetail where pid= ?";
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

        String sql = "DELETE FROM Category WHERE pid=" + id;
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
}
