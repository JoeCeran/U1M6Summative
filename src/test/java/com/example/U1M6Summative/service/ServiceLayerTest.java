package com.example.U1M6Summative.service;

import com.example.U1M6Summative.dao.*;
import com.example.U1M6Summative.model.Customer;
import com.example.U1M6Summative.model.Invoice;
import com.example.U1M6Summative.model.InvoiceItem;
import com.example.U1M6Summative.model.Item;
import com.example.U1M6Summative.service.ServiceLayer;
import com.example.U1M6Summative.viewmodel.InvoiceViewModel;
import com.example.U1M6Summative.viewmodel.ItemViewModel;
import org.apache.tomcat.jni.Local;
import org.junit.Before;
import org.junit.Test;
import java.awt.dnd.InvalidDnDOperationException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

public class ServiceLayerTest {

    ServiceLayer service;
    CustomerDao customerDao;
    InvoiceDao invoiceDao;
    InvoiceItemDao invoiceItemDao;
    ItemDao itemDao;

    @Before
    public void setUp() throws Exception {
        setUpCustomerDaoMock();
        setUpInvoiceDaoMock();
        setUpInvoiceItemDaoMock();
        setUpItemDaoMock();

        service = new ServiceLayer(customerDao, invoiceDao, invoiceItemDao, itemDao);
    }


    //Invoice Tests

    @Test
    public void saveInvoice() {

        InvoiceViewModel invoicevm = new InvoiceViewModel();

        invoicevm.setOrderDate(LocalDate.of(2019, 07, 10));
        invoicevm.setPickupDate(LocalDate.of(2019, 07,10));
        invoicevm.setReturnDate(LocalDate.of(2019, 07,15));
        invoicevm.setLateFee(new BigDecimal("5.25"));

        Customer customer = new Customer();
        customer.setFirstName("John");
        customer.setLastName("Doe");
        customer.setEmail("jdoe@email.com");
        customer.setCompany("Some Company");
        customer.setPhone("973-555-1234");
        customer = service.saveCustomer(customer);

        invoicevm.setCustomer(customer);

        InvoiceItem invoiceItem = new InvoiceItem();
        invoiceItem.setQuantity(2);
        invoiceItem.setUnityRate(new BigDecimal("1.25"));
        invoiceItem.setDiscount(new BigDecimal("1.00"));
        List<InvoiceItem> invoiceItemList = new ArrayList<>();
        invoiceItemList.add(invoiceItem);

        invoicevm.setInvoiceItems(invoiceItemList);

        invoicevm = service.saveInvoice(invoicevm);

        InvoiceViewModel fromService = service.findInvoice(invoicevm.getId());

        assertEquals(invoicevm, fromService);
    }

    @Test
    public void findInvoice() {
        List<InvoiceViewModel> invoiceList = service.findAllInvoices();
        InvoiceViewModel invoicevm1 = invoiceList.get(0);
        InvoiceViewModel invoicevm2 = service.findInvoice(1);
        assertEquals(invoicevm1, invoicevm2);
    }

    @Test
    public void findAllInvoices() {
        List<InvoiceViewModel> invoiceList = service.findAllInvoices();
        assertEquals(invoiceList.size(), 1);
    }

    @Test
    public void updateInvoice() {
    }

    @Test
    public void removeInvoice() {
    }

    //Item Tests

    @Test
    public void saveItem() {

        Item item = new Item();

        item.setName("book");
        item.setDescription("romance book");
        item.setDailyRate(new BigDecimal(7.76));

        service.saveItem(item);

        Item fromService = service.findItem(item.getId());

        assertEquals(item, fromService);
    }

    @Test
    public void findItem() {

        Item item = service.findItem(0);
        Item item2 = service.findItem(item.getId());

        assertEquals(item, item2);
    }

    @Test
    public void findAllItems() {

        List<Item> fromService = service.findAllItems();

        assertEquals(1, fromService.size());
    }

    @Test
    public void updateItem() {

    }

    @Test
    public void removeItem() {

    }

    //Set Ups
    @Test
    public void setUpCustomerDaoMock() {

        customerDao = mock(CustomerDaoJdbcTemplateImpl.class);
        Customer customer = new Customer();
        customer.setId(customer.getId());
        customer.setFirstName("Robert");
        customer.setLastName("Iger");
        customer.setCompany("Disney");
        customer.setPhone("2125551212");

        Customer customer1 = new Customer();
        customer.setId(customer.getId());
        customer.setFirstName("Robert");
        customer.setLastName("Iger");
        customer.setCompany("Disney");
        customer.setPhone("2125551212");

        List<Customer> customerList = new ArrayList<>();
        customerList.add(customer);

        doReturn(customer).when(customerDao).addCustomer(customer);
        doReturn(customer).when(customerDao).addCustomer(customer1);
        doReturn(customerList).when(customerDao).getAllCustomers();
    }

    @Test
    public void setUpInvoiceDaoMock() {


        invoiceDao = mock(InvoiceDaoJdbcTemplateImpl.class);
        Invoice invoice1 = new Invoice();
        invoice1.setId(1);
        invoice1.setCustomerId(25);
        invoice1.setOrderDate(LocalDate.of(2019, 05, 25));
        invoice1.setPickupDate(LocalDate.of(2019, 05, 26));
        invoice1.setReturnDate(LocalDate.of(2019, 05,27));
        invoice1.setLateFee(new BigDecimal("10.00"));

        Invoice invoice2 = new Invoice();
        invoice2.setCustomerId(25);
        invoice2.setOrderDate(LocalDate.of(2019, 07, 02));
        invoice2.setPickupDate(LocalDate.of(2019, 07, 03));
        invoice2.setReturnDate(LocalDate.of(2019, 07,07));
        invoice2.setLateFee(new BigDecimal("5.00"));

        List<Invoice> invoiceList = new ArrayList<>();
        invoiceList.add(invoice1);

        doReturn(invoice1).when(invoiceDao).addInvoice(invoice2);
        doReturn(invoice1).when(invoiceDao).getInvoice(1);
        doReturn(invoiceList).when(invoiceDao).getAllInvoices();
    }

    @Test
    public void setUpInvoiceItemDaoMock() {
            invoiceItemDao = mock(InvoiceItemDaoJdbcTemplateImpl.class);
            InvoiceItem invoiceItem = new InvoiceItem();
            invoiceItem.setInvoiceId(0);
            invoiceItem.setItemId(invoiceItem.getItemId());
            invoiceItem.setQuantity(1000);
            invoiceItem.setUnityRate(new BigDecimal(10.00));

            InvoiceItem invoiceItem2 = new InvoiceItem();
            invoiceItem2.setInvoiceId(0);
            invoiceItem2.setItemId(invoiceItem.getItemId());
            invoiceItem2.setQuantity(1000);
            invoiceItem2.setUnityRate(new BigDecimal(10.00));
            invoiceItem2.setDiscount(new BigDecimal(10.00));


            List<InvoiceItem> invoiceItemList = new ArrayList<>();
            invoiceItemList.add(invoiceItem);

            doReturn(invoiceItem).when(invoiceItemDao).addInvoiceItem(invoiceItem);
            doReturn(invoiceItem).when(invoiceItemDao).addInvoiceItem(invoiceItem2);
            doReturn(invoiceItem).when(invoiceItemDao).getAllInvoiceItem();
        }

    @Test
    public void setUpItemDaoMock() {
        itemDao = mock(ItemDaoJdbcTemplateImpl.class);
        Item item = new Item();
        item.setName("book");
        item.setDescription("romance book");
        item.setDailyRate(new BigDecimal(7.76));


        Item item2 = new Item();
        item.setName("book2");
        item.setDescription("fiction book");
        item.setDailyRate(new BigDecimal(7.76));
        item = itemDao.addItem(item);

        List<Item> iList = new ArrayList<>();
        iList.add(item);

        doReturn(item).when(itemDao).addItem(item2);
        doReturn(item).when(itemDao).getItem(1);
        doReturn(iList).when(itemDao).getAllItems();
    }
}