package com.example.U1M6Summative.service;

import com.example.U1M6Summative.dao.CustomerDao;
import com.example.U1M6Summative.dao.InvoiceDao;
import com.example.U1M6Summative.dao.InvoiceItemDao;
import com.example.U1M6Summative.dao.ItemDao;
import com.example.U1M6Summative.model.Item;
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



