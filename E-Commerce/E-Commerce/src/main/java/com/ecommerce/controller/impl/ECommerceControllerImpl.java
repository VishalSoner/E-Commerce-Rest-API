package com.ecommerce.controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.controller.ECommerceController;
import com.ecommerce.model.ECommerce;
import com.ecommerce.service.ECommerceService;
import com.ecommerce.wapper.Response;

@RestController
@RequestMapping("/ECommerceController")
public class ECommerceControllerImpl implements ECommerceController {

	ECommerceService eCommerceService;

	@Autowired
	ECommerceControllerImpl(ECommerceService eCommerceService) {
		this.eCommerceService = eCommerceService;
	}

	public Response create(ECommerce eCommerce) {
		return eCommerceService.create(eCommerce);
	}

	@Override
	public Response deleteById(Integer id) {
		return eCommerceService.deleteById(id);
	}

	@Override
	public Response deleteAll() {
		return eCommerceService.deleteAll();
	}

	@Override
	public Response softDelete(Integer id) {
		return eCommerceService.softDelete(id);
	}

	@Override
	public Response softBulkDelete(List<Integer> id) {
		return eCommerceService.softBulkDelete(id);
	}

	@Override
	public Response softUndoDelete(Integer id) {
		return eCommerceService.softUndoDelete(id);
	}
	
	@Override
	public Response softBulkUndoDelete(List<Integer> id) {
		return eCommerceService.softBulkUndoDelete(id);
	}
	
	@Override
	public Response findById(Integer id) {
		return eCommerceService.findById(id);
	}

	@Override
	public Response findByName(String name) {
		return eCommerceService.findByName(name);
	}

	@Override
	public Response findByDescription(String description) {
		return eCommerceService.findByDescription(description);
	}

	@Override
	public Response findAll() {
		return eCommerceService.findAll();
	}

	@Override
	public Response update(ECommerce eCommerce) {
		return eCommerceService.update(eCommerce);
	}

	@Override
	public Response count() {
		return eCommerceService.count();
	}

}
