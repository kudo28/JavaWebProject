/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Entity.Bill;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Date;

/**
 *
 * @author Kudo
 */
public class BillDAO {

    Connection conn;
    DBConnection db;

    public BillDAO(DBConnection db) {
        this.db = db;
        conn = db.getConnection();
    }

    public int insertBill(Bill bill) {
        int n = 0;
        String sql = "INSERT INTO Bill([datecreate]\n"
                + ",[Cid]\n"
                + ",[total]\n"
                + ",[recName]\n"
                + ",[recPhone]\n"
                + ",[recEmail]\n"
                + ",[infor]) VALUES(?,?,?,?,?,?,?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setDate(1, bill.getDateCreated());
            pre.setInt(2, bill.getC_ID());
            pre.setInt(3, bill.getTotal());
            pre.setString(4, bill.getRecName());
            pre.setString(5, bill.getRecPhone());
            pre.setString(6, bill.getRecEmail());
            pre.setString(7, bill.getInfor());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return n;
    }

    public int changeStatus(String bid) {
        int n = 0;
        String sql = "UPDATE Bill SET status = 1 where bid = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, bid);
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int updateBill(Bill bill) {
        int n = 0;
        String sql = "UPDATE Bill SET datecreate=?, Cid=?, recName=?, recPhone = ? , recEmail= ? , infor = ?, total = ? where bid =" + bill.getID();
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setDate(1, bill.getDateCreated());
            pre.setInt(2, bill.getC_ID());
            pre.setString(3, bill.getRecName());
            pre.setString(4, bill.getRecPhone());
            pre.setString(5, bill.getRecEmail());
            pre.setString(6, bill.getInfor());
            pre.setInt(7, bill.getTotal());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int getLastBill() {
        int n = -1;
        try {
            String sql = "SELECT MAX(bid) AS id FROM Bill";
            ResultSet rs = db.getData(sql);
            while (rs.next()) {
                n = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BillDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int deleteBill(int id) {
        int n = 0;
        //check foreign key: select id in Bill
        String checkSQL = "SELECT * from BillDetail where bid= ?";
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

        String sql = "DELETE FROM Bill WHERE bid=" + id;
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
}
