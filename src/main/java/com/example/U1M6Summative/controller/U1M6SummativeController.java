package com.example.U1M6Summative.controller;

import com.example.U1M6Summative.dao.CustomerDao;
import com.example.U1M6Summative.dao.InvoiceDao;
import com.example.U1M6Summative.dao.InvoiceItemDao;
import com.example.U1M6Summative.dao.ItemDao;
import com.example.U1M6Summative.model.Customer;
import com.example.U1M6Summative.model.Invoice;
import com.example.U1M6Summative.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class U1M6SummativeController {

    @Autowired
    private CustomerDao customerDao;

    @Autowired
    private InvoiceDao invoiceDao;

    @Autowired
    private InvoiceItemDao invoiceItemDao;

    @Autowired
    private ItemDao itemDao;

    //Customer
    @RequestMapping(value = "/customer", method = RequestMethod.POST)
    public Customer addCustomer(@RequestBody Customer customer) {
        customerDao.addCustomer(customer);
        return customer;
    }

    @RequestMapping(value = "/customer/{id}", method = RequestMethod.GET)
    public Customer getCustomerById(@PathVariable(name = "id") int id) {
        return customerDao.getCustomer(id);
    }

    @RequestMapping(value = "/customer/{id}", method = RequestMethod.PUT)
    public void updateCustomer(@RequestBody Customer customer, @PathVariable(name = "id") int id) {

        customerDao.updateCustomer(customer);
    }

    @RequestMapping(value = "/customer/{id}", method = RequestMethod.DELETE)
    public void deleteCustomer(@PathVariable(name = "id") int id) {
        customerDao.deleteCustomer(id);
    }

    @RequestMapping(value = "/customers", method = RequestMethod.GET)
    public List<Customer> getAllCustomers() {
        return customerDao.getAllCustomers();
    }

    //Items
    @RequestMapping(value = "/items", method = RequestMethod.POST)
    public Item addItem(@RequestBody Item item) {
        itemDao.addItem(item);
        return item;
    }

    @RequestMapping(value = "/item/{id}", method = RequestMethod.GET)
    public Item getItemById(@PathVariable(name = "id") int id) {
        return itemDao.getItem(id);
    }

    @RequestMapping(value = "/item/{id}", method = RequestMethod.PUT)
    public void updateItem(@RequestBody Item item, @PathVariable(name = "id") int id) {

        itemDao.updateItem(item);
    }

    @RequestMapping(value = "/item/{id}", method = RequestMethod.DELETE)
    public void deleteItem(@PathVariable(name = "id") int id) {
        itemDao.deleteItem(id);
    }

    @RequestMapping(value = "/items", method = RequestMethod.GET)
    public List<Item> getAllItemss() {
        return itemDao.getAllItems();
    }

    //Invoice
    @RequestMapping(value = "/invoice", method = RequestMethod.POST)
    public Invoice addInvoice(@RequestBody Invoice invoice) {
        invoiceDao.addInvoice(invoice);
        return invoice;
    }

    @RequestMapping(value = "/invoice/{id}", method = RequestMethod.DELETE)
    public void deleteInvoice(@PathVariable(name = "id") int id) {
        invoiceDao.deleteInvoice(id);
    }

    @RequestMapping(value="/invoice/{customerId}", method = RequestMethod.GET)
    public List<Invoice> getInvoiceByCustomer(@PathVariable(name="customerId") int customerId) {

        List<Invoice> invoice = invoiceDao.getInvoicesByCustomer(customerId);
        return invoice;
    }
}
