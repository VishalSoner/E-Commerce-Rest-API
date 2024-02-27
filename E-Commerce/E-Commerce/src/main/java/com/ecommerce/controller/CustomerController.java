package com.ecommerce.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.ecommerce.model.Customer;
import com.ecommerce.wapper.Response;

public interface CustomerController {

	@PostMapping(path = "create")
	public Response create(@RequestBody Customer customer);

	@GetMapping(path = "deleteById")
	public Response deleteById(@RequestParam("id") Integer id);

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

	@GetMapping(path = "findAll")
	public Response findAll();

	@PostMapping(path = "update")
	public Response update(@RequestBody Customer customer);

	@GetMapping(path = "count")
	public Response count();
}
