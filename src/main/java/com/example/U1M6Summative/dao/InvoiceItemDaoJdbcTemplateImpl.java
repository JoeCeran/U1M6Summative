package com.example.U1M6Summative.dao;

import com.example.U1M6Summative.model.Invoice;
import com.example.U1M6Summative.model.InvoiceItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class InvoiceItemDaoJdbcTemplateImpl implements InvoiceItemDao{

        // Prepared statement strings
        private static final String INSERT_INVOICE_ITEM_SQL =
                "insert into invoice_item (invoice_id, item_id, quantity, unity_rate, discount) values (?, ?, ?, ?, ?)";

        private static final String SELECT_INVOICE_ITEM_SQL =
                "select * from invoice_item where invoice_item_id = ?";

        private static final String SELECT_ALL_INVOICE_ITEM_SQL =
                "select * from invoice_item";

        private static final String DELETE_INVOICE_ITEM_SQL =
                "delete from invoice_item where invoice_item_id = ?";

        private static final String UPDATE_INVOICE_ITEM_SQL =
                "update invoice_item set invoice_id = ?, item_id = ?, quantity = ?, unity_rate = ?, discount = ?, where id = ?";

        private static final String SELECT_INVOICE_ITEMS_BY_INVOICE =
                "select * invoice_item where invoice_id = ?";

        private JdbcTemplate jdbcTemplate;

        @Autowired
        public InvoiceItemDaoJdbcTemplateImpl(JdbcTemplate jdbcTemplate) {

            this.jdbcTemplate = jdbcTemplate;
        }

        @Override
        public InvoiceItem getInvoiceItem(int id) {
            try {
                return jdbcTemplate.queryForObject(SELECT_INVOICE_ITEM_SQL, this::mapRowToInvoiceItem, id);
            } catch (EmptyResultDataAccessException e) {
                // if nothing is returned just catch the exception and return null
                return null;
            }
        }

        @Override
        public List<InvoiceItem> getAllInvoiceItem() {
            return jdbcTemplate.query(SELECT_ALL_INVOICE_ITEM_SQL, this::mapRowToInvoiceItem);
        }

        @Override
        @Transactional
        public InvoiceItem addInvoiceItem(InvoiceItem invoiceItem) {
            jdbcTemplate.update(INSERT_INVOICE_ITEM_SQL,
                    invoiceItem.getInvoiceId(),
                    invoiceItem.getItemId(),
                    invoiceItem.getQuantity(),
                    invoiceItem.getUnityRate(),
                    invoiceItem.getDiscount());

            int id = jdbcTemplate.queryForObject("select last_insert_id()", Integer.class);

            invoiceItem.setId(id);

            return invoiceItem;
        }

        @Override
        public void updateInvoiceItem(InvoiceItem invoiceItem) {

            jdbcTemplate.update(UPDATE_INVOICE_ITEM_SQL,
                    invoiceItem.getInvoiceId(),
                    invoiceItem.getItemId(),
                    invoiceItem.getQuantity(),
                    invoiceItem.getUnityRate(),
                    invoiceItem.getDiscount());
        }

        @Override
        public void deleteInvoiceItem(int id) {

            jdbcTemplate.update(DELETE_INVOICE_ITEM_SQL, id);
        }

        public List<InvoiceItem> getInvoiceItemsByInvoice(int invoiceId){
        return jdbcTemplate.query(SELECT_INVOICE_ITEMS_BY_INVOICE, this::mapRowToInvoiceItem, invoiceId

        );
    }

        // Helper methods
        private InvoiceItem mapRowToInvoiceItem(ResultSet rs, int rowNum) throws SQLException {
            InvoiceItem invoiceItem = new InvoiceItem();
            invoiceItem.setInvoiceId(rs.getInt("invoice_id"));
            invoiceItem.setItemId(rs.getInt("item_id"));
            invoiceItem.setQuantity(rs.getInt("quantity"));
            invoiceItem.setUnityRate(rs.getBigDecimal("unity_rate"));
            invoiceItem.setDiscount(rs.getBigDecimal("discount"));

            return invoiceItem;
        }
}
