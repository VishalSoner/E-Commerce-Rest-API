package com.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ecommerce.model.ECommerce;

@Repository
public interface ECommerceRepository extends JpaRepository<ECommerce, Integer> {

	@Query(value = "SELECT * FROM E_COMMERCE e WHERE e.IS_DELETE = false;", nativeQuery = true)
	public List<ECommerce> findAll();

	@Query(value = "SELECT * FROM E_COMMERCE e WHERE e.name = :name AND e.IS_DELETE = false;", nativeQuery = true)
	public List<ECommerce> findByName(String name);

	@Query(value = "SELECT * FROM E_COMMERCE e WHERE e.description = :description AND e.IS_DELETE = false;", nativeQuery = true)
	public List<ECommerce> findByDescription(String description);

	public long countIsDelete();

}
