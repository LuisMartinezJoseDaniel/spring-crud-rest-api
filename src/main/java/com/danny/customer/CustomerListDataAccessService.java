package com.danny.customer;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


// DAO ( Data Access Object) Layer Implementation
@Repository("list")
public class CustomerListDataAccessService implements CustomerDao{
    private static List<Customer> customers;

    static {
        customers = new ArrayList<>();
        Customer alex = new Customer(1,"Alex", "alex@correo.com", 23);
        Customer jamila = new Customer(2,"Jamila", "jamila@correo.com", 23);
        customers.add(jamila);
        customers.add(alex);
    }

    @Override
    public List<Customer> selectAllCustomers() {
        return customers;
    }

    @Override
    public Optional<Customer> selectCustomerById(Integer id) {
        return customers.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst();

    }

    @Override
    public void insertCustomer(Customer customer) {
        customers.add(customer);
    }

    @Override
    public boolean existsPersonWithEmail(String email) {
        return customers.stream().anyMatch(c -> c.getEmail().equals(email));
    }

    @Override
    public boolean existsPersonWithId(Integer id) {
        return customers.stream().anyMatch(c -> c.getId().equals(id));
    }

    @Override
    public void deleteCustomerById(Integer id) {
        boolean exists = customers.stream().anyMatch(c -> c.getId().equals(id));
        if(exists){
            customers.removeIf(c -> c.getId().equals(id));
        }
    }

    @Override
    public void updateCustomer(Customer customer) {
        customers.add(customer);
    }
}
