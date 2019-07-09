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

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class CustomerDaoTest {

    @Autowired
    protected CustomerDao customerDao;
    @Autowired
    protected InvoiceDao invoiceDao;
    @Autowired
    protected InvoiceItemDao invoiceItemDao;
    @Autowired
    protected ItemDao itemDao;

    @Before
    public void setUp() throws Exception {
        // Clean up the test db
        // Clean up the test db
        List<Customer> customerList = customerDao.getAllCustomers();
        for (Customer customer : customerList) {
            customerDao.deleteCustomer(customer.getId());
        }

        List<Invoice> invoiceList = invoiceDao.getAllInvoices();
        for (Invoice invoice : invoiceList) {
            invoiceDao.deleteInvoice(invoice.getId());
        }

        List<InvoiceItem> invoiceItemList = invoiceItemDao.getAllInvoiceItem();
        for (InvoiceItem invoiceItem : invoiceItemList) {
            invoiceItemDao.deleteInvoiceItem(invoiceItem.getId());
        }

        List<Item> itemList = itemDao.getAllItems();
        for (Item item: itemList) {
            itemDao.deleteItem(item.getId());
        }
    }

    @Test
    public void addGetDeleteCustomer() {

        // Create a customer first

        Customer customer = new Customer();
        customer.setFirstName("John");
        customer.setLastName("Doe");
        customer.setEmail("jdoe@email.com");
        customer.setCompany("Some Company");
        customer.setPhone("973-555-1234");

        customer = customerDao.addCustomer(customer);


        Customer customer2 = customerDao.getCustomer(customer.getId());

        assertEquals(customer2, customer);

        customerDao.deleteCustomer(customer.getId());

        customer2 = customerDao.getCustomer(customer.getId());

        assertNull(customer2);
    }

    @Test
    public void getAllCustomers() {

        // Create customers first
        Customer customer1 = new Customer();
        customer1.setFirstName("John");
        customer1.setLastName("Doe");
        customer1.setEmail("jdoe@email.com");
        customer1.setCompany("Some Company");
        customer1.setPhone("973-555-1234");
        customer1 = customerDao.addCustomer(customer1);

        Customer customer2 = new Customer();
        customer2.setFirstName("Jane");
        customer2.setLastName("Smith");
        customer2.setEmail("jsmith@email.com");
        customer2.setCompany("Another Company");
        customer2.setPhone("973-555-1111");
        customer2 = customerDao.addCustomer(customer2);


        List<Customer> customerList = customerDao.getAllCustomers();

        assertEquals(customerList.size(), 2);
    }



    @Test
    public void updateCustomer() {

        // Create a customer first
        Customer customer = new Customer();
        customer.setFirstName("John");
        customer.setLastName("Doe");
        customer.setEmail("jdoe@email.com");
        customer.setCompany("Some Company");
        customer.setPhone("973-555-1234");

        customer = customerDao.addCustomer(customer);

        customer.setEmail("JaneDoe@email.com");
        customer.setCompany("New Company");
        customer.setPhone("973-555-5678");


        customerDao.updateCustomer(customer);

        Customer customer2 =  customerDao.getCustomer(customer.getId());

        assertEquals(customer2, customer);
    }
}