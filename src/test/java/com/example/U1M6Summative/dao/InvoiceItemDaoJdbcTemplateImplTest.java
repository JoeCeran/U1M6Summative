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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class InvoiceItemDaoJdbcTemplateImplTest {

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
    public void addGetDeleteInvoiceItem() {
        Item item = new Item();
        item.setName("Bucket");
        item.setDescription("Big Can thing");
        item.setDailyRate(new BigDecimal("1.25"));
        itemDao.addItem(item);

        // Create an invoice first
        InvoiceItem invoiceItem = new InvoiceItem();
        invoiceItem.setInvoiceId(0);
        invoiceItem.setItemId(item.getId());
        invoiceItem.setQuantity(1000);
        invoiceItem.setUnityRate(new BigDecimal(10.00));
        invoiceItem.setDiscount(new BigDecimal(10.00));
        invoiceItemDao.addInvoiceItem(invoiceItem);

        InvoiceItem invoiceItem2 = invoiceItemDao.getInvoiceItem(invoiceItem.getId());

        assertEquals(invoiceItem, invoiceItem2);

        invoiceItemDao.deleteInvoiceItem(invoiceItem.getId());

        invoiceItem2 = invoiceItemDao.getInvoiceItem(invoiceItem.getId());

        assertNull(invoiceItem2);
    }

    @Test
    public void getAllInvoices() {

        Item item = new Item();
        item.setName("Bucket");
        item.setDescription("Big Can thing");
        item.setDailyRate(new BigDecimal("1.25"));
        itemDao.addItem(item);

        // Create an invoice first
        InvoiceItem invoiceItem = new InvoiceItem();
        invoiceItem.setInvoiceId(0);
        invoiceItem.setItemId(item.getId());
        invoiceItem.setQuantity(1000);
        invoiceItem.setUnityRate(new BigDecimal(10.00));
        invoiceItem.setDiscount(new BigDecimal(10.00));
        invoiceItemDao.addInvoiceItem(invoiceItem);

        item = new Item();
        item.setName("Bucket");
        item.setDescription("Big Can thing");
        item.setDailyRate(new BigDecimal("1.25"));
        itemDao.addItem(item);

        // Create a second invoice
        InvoiceItem invoiceItem2 = new InvoiceItem();
        invoiceItem2.setInvoiceId(0);
        invoiceItem2.setItemId(item.getId());
        invoiceItem2.setQuantity(1000);
        invoiceItem2.setUnityRate(new BigDecimal(10.00));
        invoiceItem2.setDiscount(new BigDecimal(10.00));
        invoiceItemDao.addInvoiceItem(invoiceItem2);

        List<InvoiceItem> invoiceItemsList = invoiceItemDao.getAllInvoiceItem();

        assertEquals(invoiceItemsList.size(), 2);
    }

    @Test
    public void updateInvoice() {

        Item item = new Item();
        item.setName("Bucket");
        item.setDescription("Big Can thing");
        item.setDailyRate(new BigDecimal("1.25"));
        itemDao.addItem(item);

        InvoiceItem invoiceItem = new InvoiceItem();
        invoiceItem.setInvoiceId(0);
        invoiceItem.setItemId(item.getId());
        invoiceItem.setQuantity(1000);
        invoiceItem.setUnityRate(new BigDecimal(10.00));
        invoiceItem.setDiscount(new BigDecimal(10.00));
        invoiceItemDao.addInvoiceItem(invoiceItem);

        invoiceItem.setQuantity(2000);
        invoiceItem.setUnityRate(new BigDecimal(12.00));
        invoiceItem.setDiscount(new BigDecimal(11.00));

        invoiceItemDao.updateInvoiceItem(invoiceItem);

        InvoiceItem invoiceItem2 = invoiceItemDao.getInvoiceItem(invoiceItem.getId());

        assertEquals(invoiceItem2, invoiceItem);
    }

    @Test
    public void getInvoiceItemsByInvoice(){

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

        Invoice invoice = new Invoice();
        invoice.setCustomerId(customer1.getId());
        invoice.setOrderDate(LocalDate.of(2019,11,11));
        invoice.setPickupDate(LocalDate.of(2019,12,12));
        invoice.setReturnDate(LocalDate.of(2019,10,10));
        invoice.setLateFee(new BigDecimal(3.33));
        invoice = invoiceDao.addInvoice(invoice);

        Invoice invoice1 = new Invoice();
        invoice1.setCustomerId(customer2.getId());
        invoice1.setOrderDate(LocalDate.of(2019,07,23));
        invoice1.setPickupDate(LocalDate.of(2019,8,89));
        invoice1.setReturnDate(LocalDate.of(2019,8,13));
        invoice1.setLateFee(new BigDecimal(4.44));

        InvoiceItem invoiceItem = new InvoiceItem();
        invoiceItem.setInvoiceId(invoice.getId());
        invoiceItem.setItemId(2);
        invoiceItem.setQuantity(44);
        invoiceItem.setUnityRate(new BigDecimal(11.22));
        invoiceItem.setDiscount(new BigDecimal(3.50));
        invoiceItem = invoiceItemDao.addInvoiceItem(invoiceItem);

        InvoiceItem invoiceItem1 = new InvoiceItem();
        invoiceItem1.setInvoiceId(invoice1.getId());
        invoiceItem1.setItemId(3);
        invoiceItem1.setQuantity(45);
        invoiceItem1.setUnityRate(new BigDecimal(14.22));
        invoiceItem1.setDiscount(new BigDecimal(9.50));
        invoiceItem1 = invoiceItemDao.addInvoiceItem(invoiceItem1);

        List<InvoiceItem> invoiceItemList=invoiceItemDao.getInvoiceItemsByInvoice(invoice.getId());
        assertEquals(invoiceItemList.size(),1);

        invoiceItemList = invoiceItemDao.getInvoiceItemsByInvoice(invoice1.getId());
        assertEquals(invoiceItemList.size(),1);



    }
}
