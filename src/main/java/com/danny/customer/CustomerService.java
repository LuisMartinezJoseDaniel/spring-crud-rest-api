package com.danny.customer;

import com.danny.exception.DuplicateResourceException;
import com.danny.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerDao customerDao;

    // @Qualifier("jpa") is used to specify which implementation of CustomerDao we want to use
    public CustomerService(@Qualifier("jpa") CustomerDao customerDao){
        this.customerDao = customerDao;
    }
    public List<Customer> getAllCustomers(){
        return this.customerDao.selectAllCustomers();
    }

    public Customer getCustomerById(Integer id){
        // ResourceNotFound is a custom Class and a subclass of RuntimeException which is an unchecked exception
        return this.customerDao.selectCustomerById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Customer [%s] does not exist", id)));
    }

    public void addCustomer(CustomerRegistrationRequest customerRegistrationRequest){
        // Check if email is taken
        String email = customerRegistrationRequest.email();
        if(customerDao.existsPersonWithEmail(email)){
            throw new DuplicateResourceException("Email already taken");
        }
        // add customer
        Customer customer = new Customer(
                customerRegistrationRequest.name(),
                customerRegistrationRequest.email(),
                customerRegistrationRequest.age()
        );

        this.customerDao.insertCustomer(customer);
    }

    public void updateCustomer( Integer  id, CustomerUpdateRequest customerUpdateRequest ){
        Customer customer = this.getCustomerById(id);
        boolean emailTaken = this.customerDao.existsPersonWithEmail(customerUpdateRequest.email());
        boolean changes = false; // Check if there are changes to the customer, if not throw an exception

        // Check each field for changes
        if(customerUpdateRequest.email() != null && !customer.getEmail().equals(customerUpdateRequest.email())){
            // If email is different from the current email but the new email is taken from another customer throw an exception
            if(emailTaken) throw new DuplicateResourceException("Email already taken");
            customer.setEmail(customerUpdateRequest.email());
            changes = true;
        }
        if(customerUpdateRequest.name() != null && !customer.getName().equals(customerUpdateRequest.name())){
            customer.setName(customerUpdateRequest.name());
            changes = true;
        }
        if(customerUpdateRequest.age() != null && !customer.getAge().equals(customerUpdateRequest.age())){
            customer.setAge(customerUpdateRequest.age());
            changes = true;
        }

        if(!changes) throw new DuplicateResourceException("No changes were made");

        this.customerDao.updateCustomer(customer);

    }

    public void deleteCustomer(Integer id){
        if(!this.customerDao.existsPersonWithId(id)){
            throw new ResourceNotFoundException("Customer with [%s] does not exist".formatted(id));
        }
        this.customerDao.deleteCustomerById(id);
    }


}
