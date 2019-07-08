package com.example.U1M6Summative.dao;

import com.example.U1M6Summative.model.Customer;
import com.example.U1M6Summative.model.Invoice;
import com.example.U1M6Summative.model.InvoiceItem;
import com.example.U1M6Summative.model.Item;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class InvoiceDaoTest {

    @Autowired
    protected CustomerDao customerDao;
    @Autowired
    protected InvoiceDao invoiceDao;
    @Autowired
    protected InvoiceItemDao invoiceItemDao;
    @Autowired
    protected ItemDao itemDao;

//    @Before
//    public void setUp() throws Exception {
//        // Clean up the test db
//        List<Customer> customerList = customerDao.getAllCustomers();
//        for (Customer customer : customerList) {
//            customerDao.deleteCustomer(customer.getId());
//        }
//
//        List<Invoice> invoiceList = invoiceDao.getAllInvoices();
//        for (Invoice invoice : invoiceList) {
//            invoiceDao.deleteInvoice(invoice.getId());
//        }
//
//        List<InvoiceItem> invoiceItemList = invoiceItemDao.getAllInvoiceItems();
//        for (InvoiceItem invoiceItem : invoiceItemList) {
//            invoiceItemDao.deleteInvoiceItem(invoiceItem.getId());
//        }
//
//        List<Item> itemList = itemDao.getAllItems();
//        for (Item item: itemList) {
//            itemDao.deleteItem(item.getId());
//        }
//    }

    @Test
    public void addGetDeleteInvoice() {

        // Create a customer first
        Customer customer = new Customer();



    }

    @Test
    public void getAllInvoices() {
    }

    @Test
    public void getInvoicesByCustomer() {

    }

    @Test
    public void updateInvoice() {
    }

    @Test
    public void deleteInvoice() {
    }
}