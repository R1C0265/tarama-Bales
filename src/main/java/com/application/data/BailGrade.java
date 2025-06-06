package com.application.data;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity
public class BailGrade extends AbstractEntity   {

    private Integer gradeNumber; // e.g., "A", "B", "C"
    private Integer quantity; // Number of items in this grade
    private Integer pricePerItem; // Price per item for this grade

    @ManyToOne
    private Bail bail; // Reference to the parent Bail

    public Integer getGradeNumber() {
        return gradeNumber;
    }

    public void setGradeNumber(Integer gradeNumber) {
        this.gradeNumber = gradeNumber;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getPricePerItem() {
        return pricePerItem;
    }

    public void setPricePerItem(Integer pricePerItem) {
        this.pricePerItem = pricePerItem;
    }

    public Bail getBail() {
        return bail;
    }

    public void setBail(Bail bail) {
        this.bail = bail;
    }
}
