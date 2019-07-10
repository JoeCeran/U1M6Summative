package com.example.U1M6Summative.service;

import com.example.U1M6Summative.dao.*;
import com.example.U1M6Summative.model.Customer;
import com.example.U1M6Summative.model.Item;
import com.example.U1M6Summative.service.ServiceLayer;
import com.example.U1M6Summative.viewmodel.ItemViewModel;
import org.junit.Before;
import org.junit.Test;

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
    private void setUpCustomerDaoMock() {

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

    private void setUpInvoiceDaoMock() {

    }

    private void setUpInvoiceItemDaoMock() {

    }

    private void setUpItemDaoMock() {
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