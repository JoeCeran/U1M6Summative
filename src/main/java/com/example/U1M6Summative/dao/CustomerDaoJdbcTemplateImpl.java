package com.example.U1M6Summative.dao;

import com.example.U1M6Summative.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class CustomerDaoJdbcTemplateImpl implements CustomerDao {


    private static final String INSERT_CUSTOMER_SQL =
            "insert into customer (first_name, last_name, email, company, phone) values (?, ?, ?, ?, ?)";

    private static final String SELECT_CUSTOMER_SQL =
            "select * from customer where customer_id = ?";

    private static final String SELECT_ALL_CUSTOMERS_SQL =
            "select * from customer";

    private static final String UPDATE_CUSTOMER_SQL =
            "update customer set first_name = ?, last_name = ?, email = ?, company = ?, phone = ? where customer_id = ?";

    private static final String DELETE_CUSTOMER_SQL =
            "delete from customer where customer_id = ?";

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public CustomerDaoJdbcTemplateImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional
    public Customer addCustomer(Customer customer) {
        jdbcTemplate.update(INSERT_CUSTOMER_SQL, customer.getFirstName(), customer.getLastName(), customer.getEmail(), customer.getCompany(), customer.getPhone());

        int id = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);

        customer.setId(id);
        return customer;
    }

    @Override
    public Customer getCustomer(int id) {

        try {
            return jdbcTemplate.queryForObject(SELECT_CUSTOMER_SQL, this::mapRowToArtist, id);
        } catch (EmptyResultDataAccessException e) {
            // if there is no Artist with this id, just return null
            return null;
        }
    }

    @Override
    public List<Customer> getAllCustomers() {
        return jdbcTemplate.query(SELECT_ALL_CUSTOMERS_SQL, this::mapRowToArtist);
    }

    @Override
    public void updateCustomer(Customer customer) {
        jdbcTemplate.update(UPDATE_CUSTOMER_SQL, customer.getFirstName(), customer.getLastName(), customer.getEmail(), customer.getPhone(), customer.getEmail());
    }

    @Override
    public void deleteCustomer(int id) {

        jdbcTemplate.update(DELETE_CUSTOMER_SQL, id);

    }

    private Customer mapRowToArtist(ResultSet rs, int rowNum) throws SQLException {
        Customer customer = new Customer();
        customer.setId(rs.getInt("customer_id"));
        customer.setFirstName(rs.getString("first_name"));
        customer.setLastName(rs.getString("last_name"));
        customer.setEmail(rs.getString("email"));
        customer.setCompany(rs.getString("last_name"));
        customer.setPhone(rs.getString("email"));

        return customer;

    }
}