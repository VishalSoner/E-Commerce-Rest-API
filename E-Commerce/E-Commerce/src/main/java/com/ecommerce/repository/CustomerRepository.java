package com.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ecommerce.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	@Query(value = "SELECT * FROM CUSTOMER c WHERE c.IS_DELETE = false;", nativeQuery = true)
	public List<Customer> findAll();

	@Query(value = "SELECT * FROM CUSTOMER c WHERE c.customer_name = :name AND c.IS_DELETE = false;", nativeQuery = true)
	public List<Customer> findByName(String name);

	public long countIsDelete();
}
