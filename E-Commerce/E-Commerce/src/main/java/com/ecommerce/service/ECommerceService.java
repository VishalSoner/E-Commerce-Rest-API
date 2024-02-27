package com.ecommerce.service;

import java.util.List;

import com.ecommerce.model.ECommerce;
import com.ecommerce.wapper.Response;

public interface ECommerceService {

	public Response create(ECommerce eCommerce);

	public Response deleteById(Integer id);

	public Response deleteAll();

	public Response softDelete(Integer id);

	public Response softBulkDelete(List<Integer> id);

	public Response softUndoDelete(Integer id);

	public Response softBulkUndoDelete(List<Integer> id);

	public Response findById(Integer id);

	public Response findByName(String name);

	public Response findByDescription(String description);

	public Response findAll();

	public Response update(ECommerce eCommerce);

	public Response count();


}
