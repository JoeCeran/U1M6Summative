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
        // Clean up the test db
//        List<Customer> customerList = customerDao.getAllCustomers();
//        for (Customer customer : customerList) {
//            customerDao.deleteCustomer(customer.getId());
//        }

//        List<Invoice> invoiceList = invoiceDao.getAllInvoices();
//        for (Invoice invoice : invoiceList) {
//            invoiceDao.deleteInvoice(invoice.getId());
//        }

        List<InvoiceItem> invoiceItemList = invoiceItemDao.getAllInvoiceItem();
        for (InvoiceItem invoiceItem : invoiceItemList) {
            invoiceItemDao.deleteInvoiceItem(invoiceItem.getId());
        }

//        List<Item> itemList = itemDao.getAllItems();
//        for (Item item: itemList) {
//            itemDao.deleteItem(item.getId());
//        }
    }

    @Test
    public void addGetDeleteInvoiceItem() {

        // Create an invoice first
        InvoiceItem invoiceItem = new InvoiceItem();
        invoiceItem.setInvoiceId(0);
        invoiceItem.setItemId(0);
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
        // Create an invoice first
        InvoiceItem invoiceItem = new InvoiceItem();
        invoiceItem.setInvoiceId(0);
        invoiceItem.setItemId(0);
        invoiceItem.setQuantity(1000);
        invoiceItem.setUnityRate(new BigDecimal(10.00));
        invoiceItem.setDiscount(new BigDecimal(10.00));
        invoiceItemDao.addInvoiceItem(invoiceItem);

        // Create a second invoice
        InvoiceItem invoiceItem2 = new InvoiceItem();
        invoiceItem2.setInvoiceId(0);
        invoiceItem2.setItemId(0);
        invoiceItem2.setQuantity(1000);
        invoiceItem2.setUnityRate(new BigDecimal(10.00));
        invoiceItem2.setDiscount(new BigDecimal(10.00));
        invoiceItemDao.addInvoiceItem(invoiceItem2);

        List<InvoiceItem> invoiceItemsList = invoiceItemDao.getAllInvoiceItem();

        assertEquals(invoiceItemsList.size(), 2);
    }

    @Test
    public void updateInvoice() {
    }
}
