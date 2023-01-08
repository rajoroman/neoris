package com.customerAccount.models.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.customerAccount.models.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
	Optional<Customer> findByCustomerId(String customerId);
}
