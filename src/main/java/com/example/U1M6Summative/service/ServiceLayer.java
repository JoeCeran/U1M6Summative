package com.example.U1M6Summative.service;

import com.example.U1M6Summative.dao.CustomerDao;
import com.example.U1M6Summative.dao.InvoiceDao;
import com.example.U1M6Summative.dao.InvoiceItemDao;
import com.example.U1M6Summative.dao.ItemDao;
<<<<<<< HEAD
import com.example.U1M6Summative.model.Item;
=======
import com.example.U1M6Summative.model.Invoice;
import com.example.U1M6Summative.model.InvoiceItem;
>>>>>>> 95659d74c40e0720eddaf5470a70a2882972f2a0
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
<<<<<<< HEAD
    //Customer API

    //Invoice API

    //Item API
    public Item saveItem(Item item) {

        return itemDao.addItem(item);
    }

    public Item findItem(int id) {

        return itemDao.getItem(id);
    }

    public List<Item> findAllItems() {

        return itemDao.getAllItems();
    }

    public void updateItem(Item item) {

        itemDao.updateItem(item);
    }

    public void removeItem(int id) {

        itemDao.deleteItem(id);
    }
}
=======
>>>>>>> 95659d74c40e0720eddaf5470a70a2882972f2a0

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


