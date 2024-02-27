package com.ecommerce.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ecommerce.model.Customer;
import com.ecommerce.repository.CustomerRepository;
import com.ecommerce.service.CustomerService;
import com.ecommerce.wapper.Response;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {

	private CustomerRepository customerRepository;
	private static final String INSIDE_ECOMMERCE_SERVICE_IMPL = "Inside Class CustomerServiceImpl Method ";

	@Autowired
	CustomerServiceImpl(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	@Override
	public Response create(Customer customer) {
		log.info(INSIDE_ECOMMERCE_SERVICE_IMPL + "create");
		Customer saveCustomer = customerRepository.save(customer);
		Response response = new Response();
		response.setStatus(HttpStatus.OK);
		response.setMessage(saveCustomer);
		return response;
	}

	@Override
	public Response deleteById(Integer id) {
		log.info(INSIDE_ECOMMERCE_SERVICE_IMPL + "deleteById");
		Optional<Customer> customer = customerRepository.findById(id);
		Response response = new Response();
		if (customer.isPresent()) {
			customerRepository.delete(customer.get());
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
		Optional<Customer> customer = customerRepository.findById(id);
		Response response = new Response();
		if (customer.isPresent()) {
			Customer customer2 = customer.get();
			customer2.setIsDelete(true);
			customerRepository.save(customer2);
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
		Optional<Customer> customer = customerRepository.findById(id);
		Response response = new Response();
		if (customer.isPresent()) {
			Customer customer2 = customer.get();
			customer2.setIsDelete(false);
			customerRepository.save(customer2);
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
		Optional<Customer> customer = customerRepository.findById(id);
		Response response = new Response();
		if (customer.isPresent() && Boolean.FALSE.equals(customer.get().getIsDelete())) {
			response.setMessage(customer.get());
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
		List<Customer> customer = customerRepository.findByName(name);
		Response response = new Response();
		response.setStatus(HttpStatus.OK);
		response.setMessage(customer);
		return response;
	}

	@Override
	public Response findAll() {
		log.info(INSIDE_ECOMMERCE_SERVICE_IMPL + "findAll");
		List<Customer> customersList = customerRepository.findAll();
		Response response = new Response();
		response.setStatus(HttpStatus.OK);
		response.setMessage(customersList);
		return response;
	}

	@Override
	public Response update(Customer customer) {
		log.info(INSIDE_ECOMMERCE_SERVICE_IMPL + "update");
		Response response = new Response();
		Customer saveCustomer = customerRepository.save(customer);
		response.setStatus(HttpStatus.OK);
		response.setMessage(saveCustomer);
		return response;
	}

	@Override
	public Response count() {
		log.info(INSIDE_ECOMMERCE_SERVICE_IMPL + "count");
		Response response = new Response();
		long count = customerRepository.countIsDelete();
		response.setMessage("Count Data : " + count);
		return response;
	}
}
