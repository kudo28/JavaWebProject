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
public class Product {

    private int p_ID;
    private String name;
    private int quantity;
    private int price;
    private String image;
    private String description;
    private String cat_ID;

    public Product(int p_ID, String name, int quantity, int price, String image, String description, String cat_ID) {
        this.p_ID = p_ID;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.image = image;
        this.description = description;
        this.cat_ID = cat_ID;
    }

    public Product(String name, int quantity, int price, String image, String description, String cat_ID) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.image = image;
        this.description = description;
        this.cat_ID = cat_ID;
    }

    public int getP_ID() {
        return p_ID;
    }

    public void setP_ID(int p_ID) {
        this.p_ID = p_ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCat_ID() {
        return cat_ID;
    }

    public void setCat_ID(String cat_ID) {
        this.cat_ID = cat_ID;
    }

}
