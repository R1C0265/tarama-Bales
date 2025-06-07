package com.application.data;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
public class BailGrade extends AbstractEntity   {

    private Integer gradeNumber; // e.g., "A", "B", "C"
    private Integer quantity; // Number of items in this grade
    private Integer pricePerItem; // Price per item for this grade
    private String recordedBy;
    private LocalDate createdDate ; // Date when the grade was created
    @ManyToOne
    @JoinColumn(name = "bail_id")
    private Bail bail; // Reference to the parent Bail




    @Override
    public String toString() {
        return "BailGrade [gradeNumber=" + gradeNumber + ", quantity=" + quantity + ", pricePerItem=" + pricePerItem
                + ", bail=" + bail + ", recordedBy=" + recordedBy + ", getId()=" + getId() + ", getVersion()="
                + getVersion() + ", getGradeNumber()=" + getGradeNumber() + ", getQuantity()=" + getQuantity()
                + ", getPricePerItem()=" + getPricePerItem() + ", getBail()=" + getBail() + ", getRecordedBy()="
                + getRecordedBy() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
                + super.toString() + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        BailGrade other = (BailGrade) obj;
        if (gradeNumber == null) {
            if (other.gradeNumber != null)
                return false;
        } else if (!gradeNumber.equals(other.gradeNumber))
            return false;
        if (quantity == null) {
            if (other.quantity != null)
                return false;
        } else if (!quantity.equals(other.quantity))
            return false;
        if (pricePerItem == null) {
            if (other.pricePerItem != null)
                return false;
        } else if (!pricePerItem.equals(other.pricePerItem))
            return false;
        if (bail == null) {
            if (other.bail != null)
                return false;
        } else if (!bail.equals(other.bail))
            return false;
        if (recordedBy == null) {
            if (other.recordedBy != null)
                return false;
        } else if (!recordedBy.equals(other.recordedBy))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((gradeNumber == null) ? 0 : gradeNumber.hashCode());
        result = prime * result + ((quantity == null) ? 0 : quantity.hashCode());
        result = prime * result + ((pricePerItem == null) ? 0 : pricePerItem.hashCode());
        result = prime * result + ((bail == null) ? 0 : bail.hashCode());
        result = prime * result + ((recordedBy == null) ? 0 : recordedBy.hashCode());
        return result;
    }

    public BailGrade(Integer gradeNumber, Integer quantity, Integer pricePerItem, Bail bail, String recordedBy) {
        this.gradeNumber = gradeNumber;
        this.quantity = quantity;
        this.pricePerItem = pricePerItem;
        this.bail = bail;
        this.recordedBy = recordedBy;
    }

    public BailGrade() {
    }

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

    public String getRecordedBy() {
        return recordedBy;
    }

    public void setRecordedBy(String recordedBy) {
        this.recordedBy = recordedBy;
    }
    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }
}
