package com.ecommerce.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.ecommerce.model.ECommerce;
import com.ecommerce.wapper.Response;

public interface ECommerceController {

	@PostMapping(path = "create")
	public Response create(@RequestBody ECommerce eCommerce);

	@GetMapping(path = "deleteById")
	public Response deleteById(@RequestParam("id") Integer id);

	@GetMapping(path = "deleteAll")
	public Response deleteAll();

	@GetMapping(path = "softDelete")
	public Response softDelete(@RequestParam("id") Integer id);

	@GetMapping(path = "softBulkDelete")
	public Response softBulkDelete(@RequestBody List<Integer> id);
	
	@GetMapping(path = "softUndoDelete")
	public Response softUndoDelete(@RequestParam("id") Integer id);
	
	@GetMapping(path = "softBulkUndoDelete")
	public Response softBulkUndoDelete(@RequestBody List<Integer> id);

	@GetMapping(path = "findById")
	public Response findById(@RequestParam("id") Integer id);

	@GetMapping(path = "findByName")
	public Response findByName(@RequestParam("name") String name);

	@GetMapping(path = "findByDescription")
	public Response findByDescription(@RequestParam("description") String description);

	@GetMapping(path = "findAll")
	public Response findAll();

	@PostMapping(path = "update")
	public Response update(@RequestBody ECommerce eCommerce);

	@GetMapping(path = "count")
	public Response count();
}
