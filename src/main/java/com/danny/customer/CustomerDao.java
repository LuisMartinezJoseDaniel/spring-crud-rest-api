package com.danny.customer;

import java.util.List;
import java.util.Optional;

public interface CustomerDao {
    List <Customer> selectAllCustomers();
    Optional<Customer> selectCustomerById(Integer id);
    void insertCustomer(Customer customer);

    boolean existsPersonWithId(Integer id);
    boolean existsPersonWithEmail(String email);
    void deleteCustomerById(Integer id);

    void updateCustomer(Customer customer);



}
