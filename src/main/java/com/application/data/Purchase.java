package com.application.data;

import jakarta.persistence.Entity;
import java.time.LocalDate;

@Entity
public class Purchase extends AbstractEntity {

    private String customerName;
    private String cashier;

    private String bailName;
    private Integer amounOfItems;
    private Integer price;
    private LocalDate purchaseDate; 



    
    public String getCustomerName() {
        return this.customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCashier() {
        return this.cashier;
    }

    public void setCashier(String cashier) {
        this.cashier = cashier;
    }
    public String getBailName() {
        return bailName;
    }
    public void setBailName(String bailName) {
        this.bailName = bailName;
    }
    public Integer getAmounOfItems() {
        return amounOfItems;
    }
    public void setAmounOfItems(Integer amounOfItems) {
        this.amounOfItems = amounOfItems;
    }
    public Integer getPrice() {
        return price;
    }
    public void setPrice(Integer price) {
        this.price = price;
    }
    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }
    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

}
