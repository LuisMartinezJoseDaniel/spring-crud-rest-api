package com.danny.customer;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/customers")
public class CustomerController {

    private final CustomerService service;

    public CustomerController(CustomerService service) {
        this.service = service;
    }

    @GetMapping
    public  List<Customer> getCustomers(){
        return this.service.getAllCustomers();
    }

    @GetMapping("{id}")
    public  Customer getCustomer(@PathVariable("id") Integer id){
        return this.service.getCustomerById(id);
    }

    @PostMapping
    public void registerCustomer(
            @RequestBody CustomerRegistrationRequest customerRegistrationRequest){
        this.service.addCustomer(customerRegistrationRequest);
    }

    @DeleteMapping("{id}")
    public void deleteCustomer(@PathVariable("id") Integer id){
        this.service.deleteCustomer(id);
    }

    @PutMapping("{id}")
    public void updateCustomer(@PathVariable Integer id, @RequestBody CustomerUpdateRequest customerRegistrationRequest){
        this.service.updateCustomer(id,customerRegistrationRequest);
    }
}
