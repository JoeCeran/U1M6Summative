package com.example.U1M6Summative.controller;

import com.example.U1M6Summative.dao.CustomerDao;
import com.example.U1M6Summative.dao.InvoiceDao;
import com.example.U1M6Summative.dao.InvoiceItemDao;
import com.example.U1M6Summative.dao.ItemDao;
import com.example.U1M6Summative.model.Customer;
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
}
