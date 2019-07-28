package com.javawiz;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.javawiz.entity.Customer;
import com.javawiz.repository.CustomerRepository;

@SpringBootApplication
public class SbInsertBatchApplication {
	
	private static final Logger logger = LoggerFactory.getLogger(SbInsertBatchApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(SbInsertBatchApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner insertCustomers(CustomerRepository customerRepository) {        
        return (args) -> {
        	Customer c1 = new Customer("James", "Gosling");
            Customer c2 = new Customer("Doug", "Lea");
            Customer c3 = new Customer("Martin", "Fowler");
            Customer c4 = new Customer("Brian", "Goetz");
            List<Customer> customers = Arrays.asList(c1, c2, c3, c4);
            customerRepository.saveAll(customers);
            
            customerRepository.findAll().forEach(data ->{
            	logger.debug("{}", data);
            });
        };
    }
}
