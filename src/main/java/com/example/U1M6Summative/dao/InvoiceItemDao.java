package com.example.U1M6Summative.dao;

import com.example.U1M6Summative.model.Invoicez;
import com.example.U1M6Summative.model.InvoiceItem;

import java.util.List;

public interface InvoiceItemDao {

    InvoiceItem addInvoiceItem(InvoiceItem invoiceItem);

    InvoiceItem getInvoiceItem(int id);

    List<InvoiceItem> getAllInvoiceItem();

    void updateInvoiceItem(InvoiceItem invoiceItem);

    void deleteInvoiceItem(int id);

}
