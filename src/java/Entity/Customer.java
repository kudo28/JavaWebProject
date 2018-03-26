/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 *
 * @author Kudo
 */
public class Customer {

    private int ID;
    private String name;
    private String phoneNumber;
    private String email;
    private String username;
    private String password;
    private int status;

    public Customer(int ID, String name, String phoneNumber, String email, String userName, String password, int status) {
        this.ID = ID;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.username = userName;
        this.password = password;
        this.status = status;
    }

    //register
    public Customer(String cname, String phone, String email, String username, String password) {
        this.name = cname;
        this.phoneNumber = phone;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Customer{" + "ID=" + ID + ", name=" + name + ", phoneNumber=" + phoneNumber + ", email=" + email + ", username=" + username + ", password=" + password + ", status=" + status + '}';
    }

}
