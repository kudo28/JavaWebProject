/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Entity.BillDetail;
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
public class BillDetailDAO {

    Connection conn;

    public BillDetailDAO(DBConnection db) {
        conn = db.getConnection();
    }

    public int insertBillDetail(BillDetail billDetail) {
        int n = 0;
        String sql = "INSERT INTO [BillDetail]\n"
                + "           ([bid]\n"
                + "           ,[pid]\n"
                + "           ,[quantity]\n"
                + "           ,[price]) VALUES(?,?,?,?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, billDetail.getB_ID());
            pre.setInt(2, billDetail.getP_ID());
            pre.setInt(3, billDetail.getQuantity());
            pre.setInt(4, billDetail.getPrice());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return n;
    }

    public int updateBillDetail(int bid, int pid, int quantity, int price) {
        int n = 0;
        String sql = "UPDATE BillDetail SET bid=?, pid=?, quantity = ? , price = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, bid);
            pre.setInt(2, pid);
            pre.setInt(3, quantity);
            pre.setInt(4, price);

            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int deleteBillDetail(int bid, int pid) {
        int n = 0;
        //check foreign key: select id in Bill

        String sql = "DELETE FROM Bill WHERE bid=" + bid + "AND pid =" + pid;
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
}
