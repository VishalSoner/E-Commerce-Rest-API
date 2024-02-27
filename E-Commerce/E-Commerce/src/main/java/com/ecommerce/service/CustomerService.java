package com.ecommerce.service;

import java.util.List;

import com.ecommerce.model.Customer;
import com.ecommerce.wapper.Response;

public interface CustomerService {

	public Response create(Customer customer);

	public Response deleteById(Integer id);

	public Response softDelete(Integer id);

	public Response softBulkDelete(List<Integer> id);

	public Response softUndoDelete(Integer id);

	public Response softBulkUndoDelete(List<Integer> id);

	public Response findById(Integer id);

	public Response findByName(String name);

	public Response findAll();

	public Response update(Customer customer);

	public Response count();

}
