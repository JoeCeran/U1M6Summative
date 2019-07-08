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
    public class ItemDaoTest {

        @Autowired
        ItemDao itemDao;
        @Autowired
        CustomerDao customerDao;
        @Autowired
        InvoiceDao invoiceDao;
        @Autowired
        InvoiceItemDao invoiceItemDao;



        @Before
        public void setUp() throws Exception {

            List<Item> iList = itemDao.getAllItems();
            for (Item i : iList) {
                itemDao.deleteItem(i.getId());
            }

        }


        @Test
        public void addGetDeleteItem () {


            Item item = new Item();
            item.setName("book");
            item.setDescription("romance book");
            item.setDailyRate(new BigDecimal(7.76));
            item = itemDao.addItem(item);

            Item item1 = itemDao.getItem(item.getId());

            assertEquals(item, item1);

            itemDao.deleteItem(item.getId());

            item1 = itemDao.getItem(item.getId());

            assertNull(item1);
        }


        @Test
        public void getAllItems () {

            Item item = new Item();
            item.setName("book");
            item.setDescription("romance book");
            item.setDailyRate(new BigDecimal(7.76));

            item = itemDao.addItem(item);

            item = new Item();
            item.setName("booklet");
            item.setDescription("city booklet");
            item.setDailyRate(new BigDecimal(8.76));

            item = itemDao.addItem(item);

            List<Item> iList = itemDao.getAllItems();
            assertEquals(iList.size(), 2);
        }

        @Test
        public void updateItem () {
            Item item = new Item();
            item.setName("book");
            item.setDescription("romance book");
            item.setDailyRate(new BigDecimal(7.76));

            item = itemDao.addItem(item);


            item.setName("dictionary");
            item.setDescription("spanish dictionary");
            item.setDailyRate(new BigDecimal(17.76));

            itemDao.updateItem(item);

            Item item1 = itemDao.getItem(item.getId());

            assertEquals(item1, item);
        }


    }

