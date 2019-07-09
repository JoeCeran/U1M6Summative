package com.example.U1M6Summative.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class Invoice {

    // Properties
    private Integer id;
    private Integer customerId;
    private LocalDate orderDate;
    private LocalDate pickupDate;
    private LocalDate returnDate;
    private BigDecimal lateFee;

    // Getters and Setters
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getCustomerId() {
        return customerId;
    }
    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }
    public LocalDate getOrderDate() {
        return orderDate;
    }
    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }
    public LocalDate getPickupDate() {
        return pickupDate;
    }
    public void setPickupDate(LocalDate pickupDate) {
        this.pickupDate = pickupDate;
    }
    public LocalDate getReturnDate() {
        return returnDate;
    }
    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }
    public BigDecimal getLateFee() {
        return lateFee;
    }
    public void setLateFee(BigDecimal lateFee) {
        this.lateFee = lateFee;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Invoice that = (Invoice) o;
        return getId() == that.getId() &&
                getCustomerId() == that.getCustomerId() &&
                Objects.equals(getOrderDate(), that.getOrderDate())  &&
                Objects.equals(getPickupDate(), that.getPickupDate()) &&
                Objects.equals(getReturnDate(), that.getReturnDate()) &&
                getLateFee() == that.getLateFee();
    }
    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCustomerId(), getOrderDate(), getPickupDate(), getReturnDate(), getLateFee());
    }
}