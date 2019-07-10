package com.example.U1M6Summative.service;

import com.example.U1M6Summative.dao.*;
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

    private void setUpCustomerDaoMock() {

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