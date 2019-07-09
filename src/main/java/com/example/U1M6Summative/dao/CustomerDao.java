package com.example.U1M6Summative.dao;

import com.example.U1M6Summative.model.Customer;
import com.example.U1M6Summative.model.Invoice;
import com.example.U1M6Summative.model.Item;

import java.util.List;

public interface CustomerDao {


    Customer addCustomer (Customer customer);

    static Customer getCustomer(int id);

    static List<Customer> getAllCustomers();

    void updateCustomer(Customer customer);

    static void deleteCustomer(int id);


}