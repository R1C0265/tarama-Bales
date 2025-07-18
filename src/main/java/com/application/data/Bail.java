package com.application.data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;


@Entity
public class Bail extends AbstractEntity {

    private String bailName;
    private Integer amountOfItemsAtPurchase;
    private Integer currentAmountOfItems;
    private Integer bailPrice;
    private LocalDate dateOfPurchase;
    private String recordedBy;
    @OneToMany(mappedBy = "bail", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BailGrade> grades = new ArrayList<>();

    public List<BailGrade> getGrades() {
        return grades;
    }

    public void setGrades(List<BailGrade> grades) {
        this.grades = grades;
    }
    public void setCurrentAmountOfItems(Integer currentAmountOfItems) {
        this.currentAmountOfItems = currentAmountOfItems;
    }
    public Integer getCurrentAmountOfItems() {
        return currentAmountOfItems;
    }

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
    public Integer getamountOfItemsAtPurchase() {
        return amountOfItemsAtPurchase;
    }
    public void setamountOfItemsAtPurchase(Integer amountOfItemsAtPurchase) {
        this.amountOfItemsAtPurchase = amountOfItemsAtPurchase;
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

