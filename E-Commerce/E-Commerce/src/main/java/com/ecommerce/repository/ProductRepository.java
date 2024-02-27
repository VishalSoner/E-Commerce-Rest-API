package com.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ecommerce.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

	@Query(value = "SELECT * FROM Product e WHERE e.IS_DELETE = false;", nativeQuery = true)
	public List<Product> findAll();

	@Query(value = "SELECT * FROM Product e WHERE e.PRODUCT_NAME = :name AND e.IS_DELETE = false;", nativeQuery = true)
	public List<Product> findByName(String name);

	@Query(value = "SELECT * FROM Product e WHERE e.description = :description AND e.IS_DELETE = false;", nativeQuery = true)
	public List<Product> findByDescription(String description);

	public long countIsDelete();

}
