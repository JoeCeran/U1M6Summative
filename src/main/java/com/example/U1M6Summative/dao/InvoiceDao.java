package com.example.U1M6Summative.dao;

import com.example.U1M6Summative.model.Invoice;
import com.example.U1M6Summative.model.Item;

import java.util.List;

public interface InvoiceDao {

    Invoice addInvoice(Invoice invoice);

    Invoice getInvoice(int id);

    List<Invoice> getAllInvoices();

    List<Invoice> getInvoicesByCustomer(int customerId);



    void updateInvoice(Invoice invoice);

    void deleteInvoice(int id);
}