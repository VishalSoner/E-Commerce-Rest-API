package com.ecommerce.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ecommerce.model.Product;
import com.ecommerce.repository.ProductRepository;
import com.ecommerce.service.ProductService;
import com.ecommerce.wapper.Response;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

	private ProductRepository productRepository;
	private static final String INSIDE_ECOMMERCE_SERVICE_IMPL = "Inside Class ProductServiceImpl Method ";

	@Autowired
	ProductServiceImpl(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	@Override
	public Response create(Product product) {
		log.info(INSIDE_ECOMMERCE_SERVICE_IMPL + "create");
		Product saveProduct = productRepository.save(product);
		Response response = new Response();
		response.setStatus(HttpStatus.OK);
		response.setMessage(saveProduct);
		return response;
	}

	@Override
	public Response deleteById(Integer id) {
		log.info(INSIDE_ECOMMERCE_SERVICE_IMPL + "deleteById");
		Optional<Product> product = productRepository.findById(id);
		Response response = new Response();
		if (product.isPresent()) {
			productRepository.delete(product.get());
			response.setStatus(HttpStatus.OK);
			response.setMessage("Data Deleted Successfully");
			return response;
		} else {
			response.setStatus(HttpStatus.NOT_FOUND);
			return response;
		}
	}

	@Override
	public Response softDelete(Integer id) {
		log.info(INSIDE_ECOMMERCE_SERVICE_IMPL + "softDelete");
		Optional<Product> product = productRepository.findById(id);
		Response response = new Response();
		if (product.isPresent()) {
			Product product2 = product.get();
			product2.setIsDelete(true);
			productRepository.save(product2);
			response.setStatus(HttpStatus.OK);
			response.setMessage("Data Soft Deleted Successfully");
			return response;
		} else {
			response.setStatus(HttpStatus.NOT_FOUND);
			return response;
		}
	}

	@Override
	public Response softBulkDelete(List<Integer> id) {
		log.info(INSIDE_ECOMMERCE_SERVICE_IMPL + "softBulkDelete");
		Response response = new Response();
		for (Integer list : id) {
			softDelete(list);
			response.setMessage("Data Soft Bulk Deleted Successfully");
			response.setStatus(HttpStatus.OK);
		}
		return response;
	}

	@Override
	public Response softUndoDelete(Integer id) {
		log.info(INSIDE_ECOMMERCE_SERVICE_IMPL + "softUndoDelete");
		Optional<Product> product = productRepository.findById(id);
		Response response = new Response();
		if (product.isPresent()) {
			Product product2 = product.get();
			product2.setIsDelete(false);
			productRepository.save(product2);
			response.setStatus(HttpStatus.OK);
			response.setMessage("Data Undo Successfully");
			return response;
		} else {
			response.setStatus(HttpStatus.NOT_FOUND);
			return response;
		}
	}

	@Override
	public Response softBulkUndoDelete(List<Integer> id) {
		log.info(INSIDE_ECOMMERCE_SERVICE_IMPL + "softBulkUndoDelete");
		Response response = new Response();
		for (Integer list : id) {
			softUndoDelete(list);
			response.setMessage("Data Bolk Undo Successfully");
			response.setStatus(HttpStatus.OK);
		}
		return response;
	}

	@Override
	public Response findById(Integer id) {
		log.info(INSIDE_ECOMMERCE_SERVICE_IMPL + "findById");
		Optional<Product> product = productRepository.findById(id);
		Response response = new Response();
		if (product.isPresent() && Boolean.FALSE.equals(product.get().getIsDelete())) {
			response.setMessage(product.get());
			response.setStatus(HttpStatus.OK);
			return response;
		} else {
			response.setStatus(HttpStatus.NOT_FOUND);
			return response;
		}
	}

	@Override
	public Response findByName(String name) {
		log.info(INSIDE_ECOMMERCE_SERVICE_IMPL + "findByName");
		List<Product> product = productRepository.findByName(name);
		Response response = new Response();
		response.setStatus(HttpStatus.OK);
		response.setMessage(product);
		return response;
	}

	@Override
	public Response findByDescription(String description) {
		log.info(INSIDE_ECOMMERCE_SERVICE_IMPL + "findByDescription");
		List<Product> product = productRepository.findByDescription(description);
		Response response = new Response();
		response.setStatus(HttpStatus.OK);
		response.setMessage(product);
		return response;
	}

	@Override
	public Response findAll() {
		log.info(INSIDE_ECOMMERCE_SERVICE_IMPL + "findAll");
		List<Product> productsList = productRepository.findAll();
		Response response = new Response();
		response.setStatus(HttpStatus.OK);
		response.setMessage(productsList);
		return response;
	}

	@Override
	public Response update(Product product) {
		log.info(INSIDE_ECOMMERCE_SERVICE_IMPL + "update");
		Response response = new Response();
		Product saveProduct = productRepository.save(product);
		response.setStatus(HttpStatus.OK);
		response.setMessage(saveProduct);
		return response;
	}

	@Override
	public Response count() {
		log.info(INSIDE_ECOMMERCE_SERVICE_IMPL + "count");
		Response response = new Response();
		long count = productRepository.countIsDelete();
		response.setMessage("Count Data : " + count);
		return response;
	}

}
