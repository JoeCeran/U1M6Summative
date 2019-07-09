package com.example.U1M6Summative.model;

import java.math.BigDecimal;
import java.util.Objects;

public class InvoiceItem {

    private Integer id;
    private Integer invoiceId;
    private Integer quantity;
    private BigDecimal unityRate;
    private BigDecimal discount;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(Integer invoiceId) {
        this.invoiceId = invoiceId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getUnityRate() {
        return unityRate;
    }

    public void setUnityRate(BigDecimal unityRate) {
        this.unityRate = unityRate;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InvoiceItem that = (InvoiceItem) o;
        return getId() == that.getId() &&
                Objects.equals(getInvoiceId(), that.getInvoiceId()) &&
                Objects.equals(getQuantity(), that.getQuantity()) &&
                Objects.equals(getUnityRate(), that.getUnityRate()) &&
                Objects.equals(getDiscount(), that.getDiscount());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getInvoiceId(), getQuantity(), getUnityRate(), getDiscount());
    }
}