package com.example.U1M6Summative.service;

import com.example.U1M6Summative.dao.CustomerDao;
import com.example.U1M6Summative.dao.InvoiceDao;
import com.example.U1M6Summative.dao.InvoiceItemDao;
import com.example.U1M6Summative.dao.ItemDao;
import com.example.U1M6Summative.model.Invoice;
import com.example.U1M6Summative.model.InvoiceItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ServiceLayer {

    private CustomerDao customerDao;
    private InvoiceDao invoiceDao;
    private InvoiceItemDao invoiceItemDao;
    private ItemDao itemDao;

    @Autowired
    public ServiceLayer(CustomerDao customerDao, InvoiceDao invoiceDao, InvoiceItemDao invoiceItemDao,
                        ItemDao itemDao){

        this.customerDao = customerDao;
        this.invoiceDao = invoiceDao;
        this.invoiceItemDao = invoiceItemDao;
        this.itemDao = itemDao;
    }

    //
    // Invoice API
    //
    public Invoice saveInvoice(Invoice invoice) {

        return invoiceDao.addInvoice(invoice);
    }

    public Invoice findInvoice(int id) {

        return invoiceDao.getInvoice(id);
    }

    public List<Invoice> findAllInvoices() {

        return invoiceDao.getAllInvoices();
    }

    public void updateInvoice(Invoice invoice) {

        invoiceDao.updateInvoice(invoice);
    }

    public void removeInvoice(int id) {

        // Remove all associated invoice items first
        List<InvoiceItem> invoiceItemList = invoiceDao.getInvoiceItemsByInvoice(id);

        invoiceItemList.stream()
                .forEach(invoiceItem -> invoiceItemDao.deleteInvoiceItem(invoiceItem.getId()));

        // Remove invoice
        invoiceDao.deleteInvoice(id);
    }

}


