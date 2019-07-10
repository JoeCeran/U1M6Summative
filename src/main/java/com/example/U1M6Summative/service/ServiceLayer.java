package com.example.U1M6Summative.service;

import com.example.U1M6Summative.dao.CustomerDao;
import com.example.U1M6Summative.dao.InvoiceDao;
import com.example.U1M6Summative.dao.InvoiceItemDao;
import com.example.U1M6Summative.dao.ItemDao;
import com.example.U1M6Summative.model.Customer;
import com.example.U1M6Summative.model.Item;
import com.example.U1M6Summative.model.Invoice;
import com.example.U1M6Summative.model.InvoiceItem;
import com.example.U1M6Summative.viewmodel.InvoiceViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class ServiceLayer {

    private CustomerDao customerDao;
    private InvoiceDao invoiceDao;
    private InvoiceItemDao invoiceItemDao;
    private ItemDao itemDao;

    @Autowired
    public ServiceLayer(CustomerDao customerDao, InvoiceDao invoiceDao, InvoiceItemDao invoiceItemDao,
                        ItemDao itemDao){

        this.customerDao = customerDao;
        this.invoiceDao = invoiceDao;
        this.invoiceItemDao = invoiceItemDao;
        this.itemDao = itemDao;
    }

    //Customer API

    public Customer saveCustomer (Customer customer) {
        return customerDao.addCustomer(customer);
    }
    public Customer findCustomer (int id) {
        return customerDao.getCustomer(id);
    }
    public List<Customer> finaAlldCustomers() {
        return customerDao.getAllCustomers();
    }
    public void updateCustomer (Customer customer) {
        customerDao.updateCustomer(customer);
    }
    public void removeCustomer (int id) {
        customerDao.deleteCustomer(id);
    }

    //Invoice API
    @Transactional
    public InvoiceViewModel saveInvoice(InvoiceViewModel viewModel) {

        // Persist Invoice
        Invoice inv = new Invoice();
        inv.setCustomerId(viewModel.getCustomer().getId());
        inv.setOrderDate(viewModel.getOrderDate());
        inv.setPickupDate(viewModel.getPickupDate());
        inv.setReturnDate(viewModel.getReturnDate());
        inv.setLateFee(viewModel.getLateFee());
        inv = invoiceDao.addInvoice(inv);
        viewModel.setId(inv.getId());

        // Add Invoice Id to Invoice Items and Persist Invoice Items
        List<InvoiceItem> invoiceItems = viewModel.getInvoiceItems();

        invoiceItems.stream()
                .forEach(invoiceItem -> {
                    invoiceItem.setInvoiceId(viewModel.getId());
                    invoiceItemDao.addInvoiceItem(invoiceItem);
                });

        invoiceItems = invoiceItemDao.getInvoiceItemsbyInvoice(viewModel.getId());
        viewModel.setInvoiceItems(invoiceItems);

        return viewModel;
    }

    public InvoiceViewModel findInvoice(int id) {

        // Get the invoice object first
        Invoice invoice = invoiceDao.getInvoice(id);

        return buildInvoiceViewModel(invoice);
    }

    public List<InvoiceViewModel> findAllInvoices() {

        List<Invoice> invoiceList = invoiceDao.getAllInvoices();

        List<InvoiceViewModel> invoicevmList = new ArrayList<>();

        for (Invoice invoice: invoiceList) {
            InvoiceViewModel invoicevm = buildInvoiceViewModel(invoice);
            invoicevmList.add(invoicevm);
        }

        return invoicevmList;
    }

    @Transactional
    public void updateInvoice(InvoiceViewModel viewModel) {

        // Update the invoice information
        Invoice invoice = new Invoice();
        invoice.setId(viewModel.getId());
        invoice.setCustomerId(viewModel.getCustomer().getId());
        invoice.setOrderDate(viewModel.getOrderDate());
        invoice.setPickupDate(viewModel.getPickupDate());
        invoice.setReturnDate(viewModel.getReturnDate());
        invoice.setLateFee(viewModel.getLateFee());
        invoiceDao.updateInvoice(invoice);

        // We don't know if any invoice items have been removed so delete all associated invoice items
        // and then associate the invoice items in the viewModel with the invoice
        List<InvoiceItem> invoiceItemList = invoiceItemDao.getInvoiceItemsByInvoice(invoice.getId());
        invoiceItemList.stream()
                .forEach(invoiceItem -> invoiceItemDao.deleteInvoiceItem(invoiceItem.getId()));

        List<InvoiceItem> invoiceItems = viewModel.getInvoiceItems();
        invoiceItems.stream()
                .forEach(invoiceItem ->
                {
                    invoiceItem.setInvoiceId(viewModel.getId());
                    invoiceItem = invoiceItemDao.addInvoiceItem(invoiceItem);
                });
    }

    @Transactional
    public void removeInvoice(int id) {

        // Remove all associated invoice items first
        List<InvoiceItem> invoiceItemList = invoiceItemDao.getInvoiceItemsByInvoice(id);

        invoiceItemList.stream()
                .forEach(invoiceItem -> invoiceItemDao.deleteInvoiceItem(invoiceItem.getId()));

        // Remove invoice
        invoiceDao.deleteInvoice(id);
    }

    //Item API
    public Item saveItem(Item item) {

        return itemDao.addItem(item);
    }


    public Item findItem(int id) {

        return itemDao.getItem(id);
    }

    public List<Item> findAllItems() {

        return itemDao.getAllItems();
    }

    public void updateItem(Item item) {

        itemDao.updateItem(item);
    }

    public void removeItem(int id) {

        itemDao.deleteItem(id);
    }

    // Helper Methods
    private InvoiceViewModel buildInvoiceViewModel(Invoice invoice) {

        // Get the associated customer
        Customer customer = customerDao.getCustomer(invoice.getCustomerId());

        // Get the invoice items associated with the invoice
        List<InvoiceItem> invoiceItemList = invoiceItemDao.getInvoiceItemsByInvoice(invoice.getId());

        // Remove all associated invoice items first
        invoiceItemList.stream()
        .forEach(invoiceItem -> invoiceItemDao.deleteInvoiceItem(invoiceItem.getId()));

        // Assemble the InvoiceViewModel
        InvoiceViewModel invoicevm = new InvoiceViewModel();
        invoicevm.setId(invoice.getId());
        invoicevm.setCustomer(customer);
        invoicevm.setOrderDate(invoice.getOrderDate());
        invoicevm.setPickupDate(invoice.getPickupDate());
        invoicevm.setReturnDate(invoice.getReturnDate());
        invoicevm.setLateFee(invoice.getLateFee());
        invoicevm.setInvoiceItems(invoiceItemList);

        // Return the InvoiceViewModel
        return invoicevm;
    }
}



