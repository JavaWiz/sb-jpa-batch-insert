package com.javawiz.repository;

import org.springframework.data.repository.CrudRepository;
import com.javawiz.entity.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
}