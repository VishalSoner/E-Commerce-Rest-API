package com.ecommerce.controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.controller.ProductController;
import com.ecommerce.model.Product;
import com.ecommerce.service.ProductService;
import com.ecommerce.wapper.Response;

@RestController
@RequestMapping("/ProductController")
public class ProductControllerImpl implements ProductController {

	private ProductService productService;

	@Autowired
	ProductControllerImpl(ProductService productService) {
		this.productService = productService;
	}

	public Response create(Product product) {
		return productService.create(product);
	}

	@Override
	public Response deleteById(Integer id) {
		return productService.deleteById(id);
	}

	@Override
	public Response softDelete(Integer id) {
		return productService.softDelete(id);
	}

	@Override
	public Response softBulkDelete(List<Integer> id) {
		return productService.softBulkDelete(id);
	}

	@Override
	public Response softUndoDelete(Integer id) {
		return productService.softUndoDelete(id);
	}

	@Override
	public Response softBulkUndoDelete(List<Integer> id) {
		return productService.softBulkUndoDelete(id);
	}

	@Override
	public Response findById(Integer id) {
		return productService.findById(id);
	}

	@Override
	public Response findByName(String name) {
		return productService.findByName(name);
	}

	@Override
	public Response findByDescription(String description) {
		return productService.findByDescription(description);
	}

	@Override
	public Response findAll() {
		return productService.findAll();
	}

	@Override
	public Response update(Product product) {
		return productService.update(product);
	}

	@Override
	public Response count() {
		return productService.count();
	}


}
