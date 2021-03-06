package team.csht.entity;

import javax.swing.*;
import java.io.Serializable;

/** @author MnAs & Fe */
public class Good implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private String name;
    private String merchant;
    private String buyer;
    private float price;
    private boolean existence = true;
    private Icon icon;

    public Good() {
        super();
    }
    public Good(int id, String name, float price) {
        super();
        this.id=id;
        this.name=name;
        this.price=price;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getMerchant() {
        return merchant;
    }
    public void setMerchant(String merchant) {
        this.merchant = merchant;
    }
    public String getBuyer() {
        return buyer;
    }
    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }
    public float getPrice() {
        return price;
    }
    public void setPrice(float price) {
        this.price = price;
    }
    public boolean isExistence() {
        return existence;
    }
    public void setExistence(boolean existence) {
        this.existence = existence;
    }
    public Icon getIcon() {
        return icon;
    }
    public void setIcon(Icon Pics) {
        this.icon = icon;
    }
}