package com.ecommerce.controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.controller.CustomerController;
import com.ecommerce.model.Customer;
import com.ecommerce.service.CustomerService;
import com.ecommerce.wapper.Response;

@RestController
@RequestMapping("/CustomerController")
public class CustomerControllerImpl implements CustomerController {

	private CustomerService customerService;

	@Autowired
	CustomerControllerImpl(CustomerService customerService) {
		this.customerService = customerService;
	}

	public Response create(Customer customer) {
		return customerService.create(customer);
	}

	@Override
	public Response deleteById(Integer id) {
		return customerService.deleteById(id);
	}

	@Override
	public Response softDelete(Integer id) {
		return customerService.softDelete(id);
	}

	@Override
	public Response softBulkDelete(List<Integer> id) {
		return customerService.softBulkDelete(id);
	}

	@Override
	public Response softUndoDelete(Integer id) {
		return customerService.softUndoDelete(id);
	}

	@Override
	public Response softBulkUndoDelete(List<Integer> id) {
		return customerService.softBulkUndoDelete(id);
	}

	@Override
	public Response findById(Integer id) {
		return customerService.findById(id);
	}

	@Override
	public Response findByName(String name) {
		return customerService.findByName(name);
	}

	@Override
	public Response findAll() {
		return customerService.findAll();
	}

	@Override
	public Response update(Customer customer) {
		return customerService.update(customer);
	}

	@Override
	public Response count() {
		return customerService.count();
	}

}
