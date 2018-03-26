/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.sql.Date;

/**
 *
 * @author Kudo
 */
public class Bill {

    private int ID;
    private Date dateCreated;
    private int status;
    private int C_ID;
    private int total;
    private String recName;
    private String recPhone;
    private String recEmail;
    private String infor;

    public Bill(int ID, Date dateCreated, int status, int C_ID, int total, String recName, String recPhone, String recEmail, String infor) {
        this.ID = ID;
        this.dateCreated = dateCreated;
        this.status = status;
        this.C_ID = C_ID;
        this.total = total;
        this.recName = recName;
        this.recPhone = recPhone;
        this.recEmail = recEmail;
        this.infor = infor;
    }

    public Bill(Date dateCreated, int C_ID, String recName, String recPhone, String recEmail, String infor) {
        this.dateCreated = dateCreated;
        this.C_ID = C_ID;
        this.recName = recName;
        this.recPhone = recPhone;
        this.recEmail = recEmail;
        this.infor = infor;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getC_ID() {
        return C_ID;
    }

    public void setC_ID(int C_ID) {
        this.C_ID = C_ID;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getRecName() {
        return recName;
    }

    public void setRecName(String recName) {
        this.recName = recName;
    }

    public String getRecPhone() {
        return recPhone;
    }

    public void setRecPhone(String recPhone) {
        this.recPhone = recPhone;
    }

    public String getRecEmail() {
        return recEmail;
    }

    public void setRecEmail(String recEmail) {
        this.recEmail = recEmail;
    }

    public String getInfor() {
        return infor;
    }

    public void setInfor(String infor) {
        this.infor = infor;
    }

}
