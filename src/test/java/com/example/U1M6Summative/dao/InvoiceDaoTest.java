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
public class InvoiceDaoTest {

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
    public void addGetDeleteInvoice() {

        // Create a customer first
        Customer customer = new Customer();
        customer.setFirstName("John");
        customer.setLastName("Doe");
        customer.setEmail("jdoe@email.com");
        customer.setCompany("Some Company");
        customer.setPhone("973-555-1234");
        customer = customerDao.addCustomer(customer);

        Invoice invoice = new Invoice();
        invoice.setCustomerId(customer.getId());
        invoice.setOrderDate(LocalDate.of(2019, 07, 02));
        invoice.setPickupDate(LocalDate.of(2019, 07, 05));
        invoice.setReturnDate(LocalDate.of(2019, 07, 07));
        invoice.setLateFee(new BigDecimal("5.25"));
        invoice = invoiceDao.addInvoice(invoice);

        Invoice invoice1 = invoiceDao.getInvoice(invoice.getId());

        assertEquals(invoice1, invoice);

        invoiceDao.deleteInvoice(invoice.getId());

        invoice1 = invoiceDao.getInvoice(invoice.getId());

        assertNull(invoice1);
    }

    @Test
    public void getAllInvoices() {

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

        Invoice invoice1 = new Invoice();
        invoice1.setCustomerId(customer1.getId());
        invoice1.setOrderDate(LocalDate.of(2019, 07, 02));
        invoice1.setPickupDate(LocalDate.of(2019, 07, 05));
        invoice1.setReturnDate(LocalDate.of(2019, 07, 07));
        invoice1.setLateFee(new BigDecimal("5.25"));
        invoice1 = invoiceDao.addInvoice(invoice1);

        Invoice invoice2 = new Invoice();
        invoice2.setCustomerId(customer2.getId());
        invoice2.setOrderDate(LocalDate.of(2019, 07, 03));
        invoice2.setPickupDate(LocalDate.of(2019, 07, 05));
        invoice2.setReturnDate(LocalDate.of(2019, 07, 9));
        invoice2.setLateFee(new BigDecimal("5.25"));
        invoice2 = invoiceDao.addInvoice(invoice2);

        List<Invoice> invoiceList = invoiceDao.getAllInvoices();

        assertEquals(invoiceList.size(), 2);
    }

    @Test
    public void getInvoicesByCustomer() {

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

        Invoice invoice1 = new Invoice();
        invoice1.setCustomerId(customer1.getId());
        invoice1.setOrderDate(LocalDate.of(2019, 07, 02));
        invoice1.setPickupDate(LocalDate.of(2019, 07, 05));
        invoice1.setReturnDate(LocalDate.of(2019, 07, 07));
        invoice1.setLateFee(new BigDecimal("5.25"));
        invoice1 = invoiceDao.addInvoice(invoice1);

        Invoice invoice2 = new Invoice();
        invoice2.setCustomerId(customer2.getId());
        invoice2.setOrderDate(LocalDate.of(2019, 07, 03));
        invoice2.setPickupDate(LocalDate.of(2019, 07, 05));
        invoice2.setReturnDate(LocalDate.of(2019, 07, 10));
        invoice2.setLateFee(new BigDecimal("5.25"));
        invoice2 = invoiceDao.addInvoice(invoice2);

        Invoice invoice3 = new Invoice();
        invoice3.setCustomerId(customer2.getId());
        invoice3.setOrderDate(LocalDate.of(2019, 07, 03));
        invoice3.setPickupDate(LocalDate.of(2019, 07, 05));
        invoice3.setReturnDate(LocalDate.of(2019, 07, 10));
        invoice3.setLateFee(new BigDecimal("5.25"));
        invoice3 = invoiceDao.addInvoice(invoice3);

        List<Invoice> invoiceList = invoiceDao.getInvoicesByCustomer(customer1.getId());
        assertEquals(invoiceList.size(), 1);

        invoiceList = invoiceDao.getInvoicesByCustomer(customer2.getId());
        assertEquals(invoiceList.size(), 2);
    }

    @Test
    public void updateInvoice() {

        // Create a customer first
        Customer customer = new Customer();
        customer.setFirstName("John");
        customer.setLastName("Doe");
        customer.setEmail("jdoe@email.com");
        customer.setCompany("Some Company");
        customer.setPhone("973-555-1234");
        customer = customerDao.addCustomer(customer);

        Invoice invoice = new Invoice();
        invoice.setCustomerId(customer.getId());
        invoice.setOrderDate(LocalDate.of(2019, 07, 02));
        invoice.setPickupDate(LocalDate.of(2019, 07, 05));
        invoice.setReturnDate(LocalDate.of(2019, 07, 07));
        invoice.setLateFee(new BigDecimal("5.25"));
        invoice = invoiceDao.addInvoice(invoice);

        invoice.setReturnDate(LocalDate.of(2019, 07, 15));
        invoice.setLateFee(new BigDecimal("6.50"));

        invoiceDao.updateInvoice(invoice);

        Invoice invoice1 = invoiceDao.getInvoice(invoice.getId());

        assertEquals(invoice1, invoice);
    }
}