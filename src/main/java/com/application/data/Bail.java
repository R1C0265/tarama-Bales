package com.application.data;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
public class Bail extends AbstractEntity {

    private String bailName;
    private Integer amounOfItems;
    private Integer bailPrice;
    private LocalDate dateOfPurchase;
    private String recordedBy;

    public String getRecordedBy() {
        return this.recordedBy;
    }

    public void setRecordedBy(String recordedBy) {
        this.recordedBy = recordedBy;
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

