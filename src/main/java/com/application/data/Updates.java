package com.application.data;

import jakarta.persistence.Entity;

@Entity
public class Updates extends AbstractEntity{

    /* update is to handle all changes in the database
     * 
     * record  a sale 
     * add a grade
     * add a bale 
     * created a new user
     */

    private String title;
    private String amount;
    private String category;
    private String date;
    private String recordedBy;
    private String description;
    private String image;
   

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public String getDate() {
        return date;
    }   
    public void setDate(String date) {
        this.date = date;
    }
    public String getRecordedBy() {
        return recordedBy;
    }
    public void setRecordedBy(String recordedBy) {
        this.recordedBy = recordedBy;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
}