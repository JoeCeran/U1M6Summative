package com.example.U1M6Summative.dao;

import com.example.U1M6Summative.model.Item;

import java.util.List;

public interface ItemDao {

    Item addItem(Item item);

    Item getItem(int id);

    List<Item> getAllItems();

    void updateItem(Item item);

    void deleteItem(int id);

}
