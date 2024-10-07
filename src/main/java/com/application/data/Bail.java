package com.application.data;

import jakarta.persistence.Entity;
import java.time.LocalDate;

@Entity
public class Bail extends AbstractEntity {

    private String bailName;
    private Integer amounOfItems;
    private Integer bailPrice;
    private LocalDate dateOfPurchase;

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
    public Integer getBailPrice() {
        return bailPrice;
    }
    public void setBailPrice(Integer bailPrice) {
        this.bailPrice = bailPrice;
    }
    public LocalDate getDateOfPurchase() {
        return dateOfPurchase;
    }
    public void setDateOfPurchase(LocalDate dateOfPurchase) {
        this.dateOfPurchase = dateOfPurchase;
    }

}
