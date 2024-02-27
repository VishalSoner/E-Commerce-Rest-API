package com.ecommerce.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ecommerce.model.ECommerce;
import com.ecommerce.repository.ECommerceRepository;
import com.ecommerce.service.ECommerceService;
import com.ecommerce.wapper.Response;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ECommerceServiceImpl implements ECommerceService {

	private ECommerceRepository eCommerceRepository;
	private static final String INSIDE_ECOMMERCE_SERVICE_IMPL = "Inside Class ECommerceServiceImpl Method ";

	@Autowired
	ECommerceServiceImpl(ECommerceRepository eCommerceRepository) {
		this.eCommerceRepository = eCommerceRepository;
	}

	@Override
	public Response create(ECommerce eCommerce) {
		log.info(INSIDE_ECOMMERCE_SERVICE_IMPL + "create");
		ECommerce saveECommerce = eCommerceRepository.save(eCommerce);
		Response response = new Response();
		response.setStatus(HttpStatus.OK);
		response.setMessage(saveECommerce);
		return response;
	}

	@Override
	public Response deleteById(Integer id) {
		log.info(INSIDE_ECOMMERCE_SERVICE_IMPL + "deleteById");
		Optional<ECommerce> eCommerce = eCommerceRepository.findById(id);
		Response response = new Response();
		if (eCommerce.isPresent()) {
			eCommerceRepository.delete(eCommerce.get());
			response.setStatus(HttpStatus.OK);
			response.setMessage("Data Deleted Successfully");
			return response;
		} else {
			response.setStatus(HttpStatus.NOT_FOUND);
			return response;
		}
	}

	@Override
	public Response deleteAll() {
		log.info(INSIDE_ECOMMERCE_SERVICE_IMPL + "deleteAll");
		Response response = new Response();
		eCommerceRepository.deleteAll();
		response.setStatus(HttpStatus.OK);
		response.setMessage("Data Deleted All Successfully");
		return response;
	}

	@Override
	public Response softDelete(Integer id) {
		log.info(INSIDE_ECOMMERCE_SERVICE_IMPL + "softDelete");
		Optional<ECommerce> eCommerce = eCommerceRepository.findById(id);
		Response response = new Response();
		if (eCommerce.isPresent()) {
			ECommerce eCommerce2 = eCommerce.get();
			eCommerce2.setIsDelete(true);
			eCommerceRepository.save(eCommerce2);
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
		Optional<ECommerce> eCommerce = eCommerceRepository.findById(id);
		Response response = new Response();
		if (eCommerce.isPresent()) {
			ECommerce eCommerce2 = eCommerce.get();
			eCommerce2.setIsDelete(false);
			eCommerceRepository.save(eCommerce2);
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
		Optional<ECommerce> eCommerce = eCommerceRepository.findById(id);
		Response response = new Response();
		if (eCommerce.isPresent() && Boolean.FALSE.equals(eCommerce.get().getIsDelete())) {
			response.setMessage(eCommerce);
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
		List<ECommerce> eCommerce = eCommerceRepository.findByName(name);
		Response response = new Response();
		response.setStatus(HttpStatus.OK);
		response.setMessage(eCommerce);
		return response;
	}

	@Override
	public Response findByDescription(String description) {
		log.info(INSIDE_ECOMMERCE_SERVICE_IMPL + "findByDescription");
		List<ECommerce> eCommerce = eCommerceRepository.findByDescription(description);
		Response response = new Response();
		response.setStatus(HttpStatus.OK);
		response.setMessage(eCommerce);
		return response;
	}

	@Override
	public Response findAll() {
		log.info(INSIDE_ECOMMERCE_SERVICE_IMPL + "findAll");
		List<ECommerce> eCommercesList = eCommerceRepository.findAll();
		Response response = new Response();
		response.setStatus(HttpStatus.OK);
		response.setMessage(eCommercesList);
		return response;
	}

	@Override
	public Response update(ECommerce eCommerce) {
		log.info(INSIDE_ECOMMERCE_SERVICE_IMPL + "update");
		Response response = new Response();
		ECommerce saveECommerce = eCommerceRepository.save(eCommerce);
		response.setStatus(HttpStatus.OK);
		response.setMessage(saveECommerce);
		return response;
	}

	@Override
	public Response count() {
		log.info(INSIDE_ECOMMERCE_SERVICE_IMPL + "count");
		Response response = new Response();
		long count = eCommerceRepository.countIsDelete();
		response.setMessage("Count Data : " + count);
		return response;
	}
}
