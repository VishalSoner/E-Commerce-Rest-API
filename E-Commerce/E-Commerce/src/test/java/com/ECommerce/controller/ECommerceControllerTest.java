package com.ECommerce.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.json.JSONObject;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.ECommerce.AbstractTestsClass;
import com.ecommerce.model.Category;
import com.ecommerce.model.Customer;
import com.ecommerce.model.ECommerce;
import com.ecommerce.model.Orders;
import com.ecommerce.model.Orders.DeliveryStatus;
import com.ecommerce.model.Payment;
import com.ecommerce.model.Payment.CardType;
import com.ecommerce.model.Product;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@TestMethodOrder(OrderAnnotation.class)
class ECommerceControllerTest extends AbstractTestsClass {

	private String baseUrl = "/ECommerceController/";
	private static JSONObject eCommerceJson;

	@Test
	@Order(1)
	void testCreate() throws Exception {
		String uri = baseUrl + "create";
		log.info("Inside ECommerceControllerTest Method testCreate {}", uri);

		Orders orders = new Orders();
		orders.setId(0);
		orders.setDeliveryStatus(DeliveryStatus.PENDING);

		Category category = new Category();
		category.setId(0);
		category.setName("Category Name");
		category.setType("Category Type");

		Payment payment = new Payment();
		payment.setId(0);
		payment.setBankName("Bank of Baroda");
		payment.setCardHolderName("Vishal Soner");
		payment.setCardType(CardType.CREDIT);
		payment.setCardNumber("987653210");
		payment.setSecurityCode("9876");

		Product product = new Product();
		product.setId(0);
		product.setColor("Red");
		product.setDescription("Prodoct Description");
		product.setName("Name");
		product.setSize("123");
		product.setPrice(1234);
		product.setOrders(orders);
		product.setPayment(payment);
		product.setCategory(category);

		Customer customer = new Customer();
		customer.setId(0);
		customer.setName("Vishal Soner");
		customer.setProduct(null);

		ECommerce eCommerce = new ECommerce();
		eCommerce.setId(0);
		eCommerce.setName("Name");
		eCommerce.setIsDelete(false);
		eCommerce.setDescription(uri);
		eCommerce.setCustomer(customer);
		String eCommerceString = convertObjectToJson(eCommerce);
		log.info("Inside ECommerceControllerTest Method testCreate eCommerceString {}", eCommerceString);

		MvcResult mvcResult = mvc
				.perform(MockMvcRequestBuilders.post(uri).content(eCommerceString.toString())
						.contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();
		int status = mvcResult.getResponse().getStatus();
		String response = mvcResult.getResponse().getContentAsString();
		log.info("Inside ECommerceControllerTest Method testCreate status {} Response {}", status, response);
		assertEquals(200, status);
		eCommerceJson = new JSONObject(response);
		eCommerceJson = eCommerceJson.getJSONObject("message");
		log.info("Inside ECommerceControllerTest Method testCreate eCommerceJson : {}", eCommerceJson);
	}

	@Test
	@Order(2)
	void testupdate() throws Exception {
		String uri = baseUrl + "update";
		ECommerce eCommerce = convertJsonToObject(eCommerceJson.toString(), ECommerce.class);
		eCommerce.setName("Update Name");

		String eCommerceString = convertObjectToJson(eCommerce);
		log.info("Inside ECommerceControllerTest Method testCreate eCommerceString {}", eCommerceString);
		MvcResult mvcResult = mvc
				.perform(MockMvcRequestBuilders.post(uri).content(eCommerceString)
						.contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();
		int status = mvcResult.getResponse().getStatus();
		String response = mvcResult.getResponse().getContentAsString();
		log.info("Inside ECommerceControllerTest Method testCount status {} Response {}", status, response);
		assertEquals(200, status);
	}

	@Test
	@Order(3)
	void testCount() throws Exception {
		String uri1 = baseUrl + "count";
		log.info("Inside ECommerceControllerTest Method testCount {}", uri1);
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri1).contentType(MediaType.APPLICATION_JSON_VALUE)
				.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		String response = mvcResult.getResponse().getContentAsString();
		log.info("Inside ECommerceControllerTest Method testCount status {} Response {}", status, response);
		assertEquals(200, status);
	}

	@Test
	@Order(4)
	void testSoftDelete() throws Exception {
		int id = +eCommerceJson.getInt("id");
		String uri = baseUrl + "softDelete?id=" + id;
		log.info("Inside ECommerceControllerTest Method testSoftDelete {}", uri);

		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).contentType(MediaType.APPLICATION_JSON_VALUE)
				.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		String response = mvcResult.getResponse().getContentAsString();
		log.info("Inside ECommerceControllerTest Method testSoftDelete status {} Response {}", status, response);
		assertEquals(200, status);
	}

	@Test
	@Order(5)
	void testSoftDeleteNotFound() throws Exception {
		int softDeleteIds = 0;
		String uri = baseUrl + "softDelete?id=" + softDeleteIds;
		log.info("Inside ECommerceControllerTest Method testSoftDeleteNotFound {}", uri);

		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).contentType(MediaType.APPLICATION_JSON_VALUE)
				.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		String response = mvcResult.getResponse().getContentAsString();
		log.info("Inside ECommerceControllerTest Method testSoftDeleteNotFound status {} Response {}", status,
				response);
		assertEquals(200, status);
	}

	@Test
	@Order(6)
	void testFindByIdNull() throws Exception {
		int id = eCommerceJson.getInt("id");
		String uri = baseUrl + "findById?id=" + id;
		log.info("Inside ECommerceControllerTest Method testFindByIdNull {}", uri);
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).contentType(MediaType.APPLICATION_JSON_VALUE)
				.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		String response = mvcResult.getResponse().getContentAsString();
		log.info("Inside ECommerceControllerTest Method testFindById status {} Response {}", status, response);
		assertEquals(200, status);
	}

	@Test
	@Order(7)
	void testSoftUndoDelete() throws Exception {
		String uri = baseUrl + "softUndoDelete?id=" + eCommerceJson.getInt("id");
		log.info("Inside ECommerceControllerTest Method tetSoftUndoDelete {}", uri);

		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).contentType(MediaType.APPLICATION_JSON_VALUE)
				.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		String response = mvcResult.getResponse().getContentAsString();
		log.info("Inside ECommerceControllerTest Method testSoftUndoDelete status {} Response {}", status, response);
		assertEquals(200, status);
	}

	@Test
	@Order(8)
	void testSoftUndoDeleteNotFound() throws Exception {
		int id = 0;
		String uri = baseUrl + "softUndoDelete?id=" + id;
		log.info("Inside ECommerceControllerTest Method tetSoftUndoDeleteNotFound {}", uri);

		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).contentType(MediaType.APPLICATION_JSON_VALUE)
				.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		String response = mvcResult.getResponse().getContentAsString();
		log.info("Inside ECommerceControllerTest Method testSoftUndoDeleteNotFound status {} Response {}", status,
				response);
		assertEquals(200, status);
	}

	@Test
	@Order(9)
	void testSoftBulkDelete() throws Exception {
		String uri = baseUrl + "softBulkDelete";
		log.info("Inside ECommerceControllerTest Method testSoftBulkDelete {}", uri);
		List<Integer> softBulkDeleteList = List.of(eCommerceJson.getInt("id"));
		MvcResult mvcResult = mvc
				.perform(MockMvcRequestBuilders.get(uri).content(softBulkDeleteList.toString())
						.contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();
		int status = mvcResult.getResponse().getStatus();
		String response = mvcResult.getResponse().getContentAsString();
		log.info("Inside ECommerceControllerTest Method testSoftBulkDelete status {} Response {}", status, response);
		assertEquals(200, status);
	}

	@Test
	@Order(10)
	void testSoftBulkUndoDelete() throws Exception {
		String uri = baseUrl + "softBulkUndoDelete";
		log.info("Inside ECommerceControllerTest Method testSoftBulkUndoDelete {}", uri);
		List<Integer> softBulkUndoDeleteList = List.of(eCommerceJson.getInt("id"));
		MvcResult mvcResult = mvc
				.perform(MockMvcRequestBuilders.get(uri).content(softBulkUndoDeleteList.toString())
						.contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();
		int status = mvcResult.getResponse().getStatus();
		String response = mvcResult.getResponse().getContentAsString();
		log.info("Inside ECommerceControllerTest Method testSoftBulkUndoDelete status {} Response {}", status,
				response);
		assertEquals(200, status);
	}

	@Test
	@Order(11)
	void testFindById() throws Exception {
		String uri = baseUrl + "findById?id=" + eCommerceJson.getInt("id");
		log.info("Inside ECommerceControllerTest Method testFindById {}", uri);
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).contentType(MediaType.APPLICATION_JSON_VALUE)
				.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		String response = mvcResult.getResponse().getContentAsString();
		log.info("Inside ECommerceControllerTest Method testFindById status {} Response {}", status, response);
		assertEquals(200, status);
	}

	@Test
	@Order(12)
	void testFindByIdNotFound() throws Exception {
		String uri = baseUrl + "findById?id=" + 0;
		log.info("Inside ECommerceControllerTest Method testFindById {}", uri);
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).contentType(MediaType.APPLICATION_JSON_VALUE)
				.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		String response = mvcResult.getResponse().getContentAsString();
		log.info("Inside ECommerceControllerTest Method testFindById status {} Response {}", status, response);
		assertEquals(200, status);
	}

	@Test
	@Order(13)
	void testFindByName() throws Exception {
		String uri = baseUrl + "findByName?name=" + eCommerceJson.getString("name");
		log.info("Inside ECommerceControllerTest Method testFindById {}", uri);
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).contentType(MediaType.APPLICATION_JSON_VALUE)
				.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		String response = mvcResult.getResponse().getContentAsString();
		log.info("Inside ECommerceControllerTest Method testFindById status {} Response {}", status, response);
		assertEquals(200, status);
	}

	@Test
	@Order(14)
	void testFindByDescrition() throws Exception {
		String uri = baseUrl + "findByDescription?description=" + eCommerceJson.getString("description");
		log.info("Inside ECommerceControllerTest Method testFindByDescrition {}", uri);
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).contentType(MediaType.APPLICATION_JSON_VALUE)
				.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		String response = mvcResult.getResponse().getContentAsString();
		log.info("Inside ECommerceControllerTest Method testFindByDescrition status {} Response {}", status, response);
		assertEquals(200, status);
	}

	@Test
	@Order(15)
	void testFindAll() throws Exception {
		String uri = baseUrl + "findAll";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).contentType(MediaType.APPLICATION_JSON_VALUE)
				.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		String response = mvcResult.getResponse().getContentAsString();
		log.info("Inside ECommerceControllerTest Method testFindAll status {} Response {}", status, response);
		assertEquals(200, status);
	}

	@Test
	@Order(16)
	void testDeleteById() throws Exception {
		int deleteId = eCommerceJson.getInt("id");
		String uri = baseUrl + "deleteById?id=" + deleteId;
		log.info("Inside ECommerceControllerTest Method testFindByDescrition {}", uri);
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).contentType(MediaType.APPLICATION_JSON_VALUE)
				.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		String response = mvcResult.getResponse().getContentAsString();
		log.info("Inside ECommerceControllerTest Method testFindByDescrition status {} Response {}", status, response);
		assertEquals(200, status);
	}

	@Test
	@Order(17)
	void testDeleteByIdNotFound() throws Exception {
		String uri = baseUrl + "deleteById?id=" + 0;
		log.info("Inside ECommerceControllerTest Method testFindByDescrition {}", uri);
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).contentType(MediaType.APPLICATION_JSON_VALUE)
				.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		String response = mvcResult.getResponse().getContentAsString();
		log.info("Inside ECommerceControllerTest Method testFindByDescrition status {} Response {}", status, response);
		assertEquals(200, status);
	}

	@Test
	@Order(18)
	void testDeleteAll() throws Exception {
		String uri = baseUrl + "deleteAll";
		log.info("Inside ECommerceControllerTest Method testFindAll {}", uri);
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).contentType(MediaType.APPLICATION_JSON_VALUE)
				.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		String response = mvcResult.getResponse().getContentAsString();
		log.info("Inside ECommerceControllerTest Method testFindAll status {} Response {}", status, response);
		assertEquals(200, status);
	}

	@Test
	@Order(19)
	void testDeleteAllNotFound() throws Exception {
		String uri = baseUrl + "deleteAll";
		log.info("Inside ECommerceControllerTest Method testFindAll {}", uri);
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).contentType(MediaType.APPLICATION_JSON_VALUE)
				.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		String response = mvcResult.getResponse().getContentAsString();
		log.info("Inside ECommerceControllerTest Method testFindAll status {} Response {}", status, response);
		assertEquals(200, status);
	}
}
