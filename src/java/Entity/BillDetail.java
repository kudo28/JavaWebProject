/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.math.BigDecimal;

/**
 *
 * @author Kudo
 */
public class BillDetail {

    @Override
    public String toString() {
        return "BillDetail{" + "B_ID=" + B_ID + ", P_ID=" + P_ID + ", quantity=" + quantity + ", price=" + price + '}';
    }

    private int B_ID;
    private int P_ID;
    private int quantity;
    private int price;

    public BillDetail(int B_ID, int P_ID, int quantity, int price) {
        this.B_ID = B_ID;
        this.P_ID = P_ID;
        this.quantity = quantity;
        this.price = price;
    }

    public int getB_ID() {
        return B_ID;
    }

    public void setB_ID(int B_ID) {
        this.B_ID = B_ID;
    }

    public int getP_ID() {
        return P_ID;
    }

    public void setP_ID(int P_ID) {
        this.P_ID = P_ID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

}
