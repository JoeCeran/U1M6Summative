package com.example.U1M6Summative.service;

import com.example.U1M6Summative.dao.CustomerDao;
import com.example.U1M6Summative.dao.InvoiceDao;
import com.example.U1M6Summative.dao.InvoiceItemDao;
import com.example.U1M6Summative.dao.ItemDao;

import com.example.U1M6Summative.model.Customer;
import com.example.U1M6Summative.model.Item;

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

    //customer api

    public Customer saveCustomer (Customer customer) {
        return customerDao.addCustomer(customer);
    }
    public Customer findCustomer (int id) {
        return customerDao.getCustomer(id);
    }
    public List<Customer> finaAlldCustomers() {
        return customerDao.getAllCustomers();
    }
    public void updateCustomer (Customer customer) {
        customerDao.updateCustomer(customer);
    }
    public void removeCustomer (int id) {
        customerDao.deleteCustomer(id);
    }
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
        List<InvoiceItem> invoiceItemList = invoiceDao.getInvoiceItemByInvoice(id);

        invoiceItemList.stream()
        .forEach(invoiceItem -> invoiceItemDao.deleteInvoiceItemByInvoice(invoiceItem.getId()));

        // Remove invoice
        invoiceDao.deleteInvoice(id);
        }

        }
