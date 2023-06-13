package com.danny;


import com.danny.customer.Customer;
import com.danny.customer.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication

public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    // This executes the code inside the run method when the application starts
    // This is like a constructor
    @Bean
    CommandLineRunner runner(CustomerRepository customerRepository){
        return args -> {
            Customer alex = new Customer("Alex", "alex@correo.com", 23);
            Customer jamila = new Customer("Jamila", "jamila@correo.com", 23);

            List<Customer> customers =   List.of(alex, jamila);
//            customerRepository.saveAll(customers);

        };
    }

}
