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
import com.ecommerce.model.Orders;
import com.ecommerce.model.Orders.DeliveryStatus;
import com.ecommerce.model.Payment;
import com.ecommerce.model.Payment.CardType;
import com.ecommerce.model.Product;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@TestMethodOrder(OrderAnnotation.class)
class CustomerControllerTest extends AbstractTestsClass {

	private String baseUrl = "/CustomerController/";
	private static JSONObject customerJson;

	@Test
	@Order(1)
	void testCreate() throws Exception {
		String uri = baseUrl + "create";
		log.info("Inside CustomerControllerTest Method testCreate {}", uri);

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

		String customerString = convertObjectToJson(customer);
		log.info("Inside CustomerControllerTest Method testCreate customerString {}", customerString);

		MvcResult mvcResult = mvc
				.perform(MockMvcRequestBuilders.post(uri).content(customerString.toString())
						.contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();
		int status = mvcResult.getResponse().getStatus();
		String response = mvcResult.getResponse().getContentAsString();
		log.info("Inside CustomerControllerTest Method testCreate status {} Response {}", status, response);
		assertEquals(200, status);
		customerJson = new JSONObject(response);
		customerJson = customerJson.getJSONObject("message");
		log.info("Inside CustomerControllerTest Method testCreate customerJson : {}", customerJson);
	}

	@Test
	@Order(2)
	void testupdate() throws Exception {
		String uri = baseUrl + "update";
		Customer customer = convertJsonToObject(customerJson.toString(), Customer.class);
		customer.setName("Update Name");

		String customerString = convertObjectToJson(customer);
		log.info("Inside CustomerControllerTest Method testCreate customerString {}", customerString);
		MvcResult mvcResult = mvc
				.perform(MockMvcRequestBuilders.post(uri).content(customerString)
						.contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();
		int status = mvcResult.getResponse().getStatus();
		String response = mvcResult.getResponse().getContentAsString();
		log.info("Inside CustomerControllerTest Method testCount status {} Response {}", status, response);
		assertEquals(200, status);
	}

	@Test
	@Order(3)
	void testCount() throws Exception {
		String uri1 = baseUrl + "count";
		log.info("Inside CustomerControllerTest Method testCount {}", uri1);
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri1).contentType(MediaType.APPLICATION_JSON_VALUE)
				.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		String response = mvcResult.getResponse().getContentAsString();
		log.info("Inside CustomerControllerTest Method testCount status {} Response {}", status, response);
		assertEquals(200, status);
	}

	@Test
	@Order(4)
	void testSoftDelete() throws Exception {
		int id = +customerJson.getInt("id");
		String uri = baseUrl + "softDelete?id=" + id;
		log.info("Inside CustomerControllerTest Method testSoftDelete {}", uri);

		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).contentType(MediaType.APPLICATION_JSON_VALUE)
				.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		String response = mvcResult.getResponse().getContentAsString();
		log.info("Inside CustomerControllerTest Method testSoftDelete status {} Response {}", status, response);
		assertEquals(200, status);
	}

	@Test
	@Order(5)
	void testSoftDeleteNotFound() throws Exception {
		int softDeleteIds = 0;
		String uri = baseUrl + "softDelete?id=" + softDeleteIds;
		log.info("Inside CustomerControllerTest Method testSoftDeleteNotFound {}", uri);

		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).contentType(MediaType.APPLICATION_JSON_VALUE)
				.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		String response = mvcResult.getResponse().getContentAsString();
		log.info("Inside CustomerControllerTest Method testSoftDeleteNotFound status {} Response {}", status, response);
		assertEquals(200, status);
	}

	@Test
	@Order(6)
	void testFindByIdNull() throws Exception {
		int id = customerJson.getInt("id");
		String uri = baseUrl + "findById?id=" + id;
		log.info("Inside CustomerControllerTest Method testFindByIdNull {}", uri);
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).contentType(MediaType.APPLICATION_JSON_VALUE)
				.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		String response = mvcResult.getResponse().getContentAsString();
		log.info("Inside CustomerControllerTest Method testFindById status {} Response {}", status, response);
		assertEquals(200, status);
	}

	@Test
	@Order(7)
	void testSoftUndoDelete() throws Exception {
		String uri = baseUrl + "softUndoDelete?id=" + customerJson.getInt("id");
		log.info("Inside CustomerControllerTest Method tetSoftUndoDelete {}", uri);
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).contentType(MediaType.APPLICATION_JSON_VALUE)
				.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		String response = mvcResult.getResponse().getContentAsString();
		log.info("Inside CustomerControllerTest Method testSoftUndoDelete status {} Response {}", status, response);
		assertEquals(200, status);
	}

	@Test
	@Order(8)
	void testSoftUndoDeleteNotFound() throws Exception {
		int id = 0;
		String uri = baseUrl + "softUndoDelete?id=" + id;
		log.info("Inside CustomerControllerTest Method tetSoftUndoDeleteNotFound {}", uri);

		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).contentType(MediaType.APPLICATION_JSON_VALUE)
				.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		String response = mvcResult.getResponse().getContentAsString();
		log.info("Inside CustomerControllerTest Method testSoftUndoDeleteNotFound status {} Response {}", status,
				response);
		assertEquals(200, status);
	}

	@Test
	@Order(9)
	void testSoftBulkDelete() throws Exception {
		String uri = baseUrl + "softBulkDelete";
		log.info("Inside CustomerControllerTest Method testSoftBulkDelete {}", uri);
		List<Integer> softBulkDeleteList = List.of(customerJson.getInt("id"));
		MvcResult mvcResult = mvc
				.perform(MockMvcRequestBuilders.get(uri).content(softBulkDeleteList.toString())
						.contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();
		int status = mvcResult.getResponse().getStatus();
		String response = mvcResult.getResponse().getContentAsString();
		log.info("Inside CustomerControllerTest Method testSoftBulkDelete status {} Response {}", status, response);
		assertEquals(200, status);
	}

	@Test
	@Order(10)
	void testSoftBulkUndoDelete() throws Exception {
		String uri = baseUrl + "softBulkUndoDelete";
		log.info("Inside CustomerControllerTest Method testSoftBulkUndoDelete {}", uri);
		List<Integer> softBulkUndoDeleteList = List.of(customerJson.getInt("id"));
		MvcResult mvcResult = mvc
				.perform(MockMvcRequestBuilders.get(uri).content(softBulkUndoDeleteList.toString())
						.contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();
		int status = mvcResult.getResponse().getStatus();
		String response = mvcResult.getResponse().getContentAsString();
		log.info("Inside CustomerControllerTest Method testSoftBulkUndoDelete status {} Response {}", status, response);
		assertEquals(200, status);
	}

	@Test
	@Order(11)
	void testFindById() throws Exception {
		String uri = baseUrl + "findById?id=" + customerJson.getInt("id");
		log.info("Inside CustomerControllerTest Method testFindById {}", uri);
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).contentType(MediaType.APPLICATION_JSON_VALUE)
				.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		String response = mvcResult.getResponse().getContentAsString();
		log.info("Inside CustomerControllerTest Method testFindById status {} Response {}", status, response);
		assertEquals(200, status);
	}

	@Test
	@Order(12)
	void testFindByIdNotFound() throws Exception {
		String uri = baseUrl + "findById?id=" + 0;
		log.info("Inside CustomerControllerTest Method testFindById {}", uri);
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).contentType(MediaType.APPLICATION_JSON_VALUE)
				.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		String response = mvcResult.getResponse().getContentAsString();
		log.info("Inside CustomerControllerTest Method testFindById status {} Response {}", status, response);
		assertEquals(200, status);
	}

	@Test
	@Order(13)
	void testFindByName() throws Exception {
		String uri = baseUrl + "findByName?name=" + customerJson.getString("name");
		log.info("Inside CustomerControllerTest Method testFindById {}", uri);
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).contentType(MediaType.APPLICATION_JSON_VALUE)
				.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		String response = mvcResult.getResponse().getContentAsString();
		log.info("Inside CustomerControllerTest Method testFindById status {} Response {}", status, response);
		assertEquals(200, status);
	}

	@Test
	@Order(14)
	void testFindAll() throws Exception {
		String uri = baseUrl + "findAll";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).contentType(MediaType.APPLICATION_JSON_VALUE)
				.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		String response = mvcResult.getResponse().getContentAsString();
		log.info("Inside CustomerControllerTest Method testFindAll status {} Response {}", status, response);
		assertEquals(200, status);
	}

	@Test
	@Order(15)
	void testDeleteById() throws Exception {
		int deleteId = customerJson.getInt("id");
		String uri = baseUrl + "deleteById?id=" + deleteId;
		log.info("Inside CustomerControllerTest Method testFindByDescrition {}", uri);
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).contentType(MediaType.APPLICATION_JSON_VALUE)
				.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		String response = mvcResult.getResponse().getContentAsString();
		log.info("Inside CustomerControllerTest Method testFindByDescrition status {} Response {}", status, response);
		assertEquals(200, status);
	}

	@Test
	@Order(16)
	void testDeleteByIdNotFound() throws Exception {
		String uri = baseUrl + "deleteById?id=" + 0;
		log.info("Inside CustomerControllerTest Method testFindByDescrition {}", uri);
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).contentType(MediaType.APPLICATION_JSON_VALUE)
				.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		String response = mvcResult.getResponse().getContentAsString();
		log.info("Inside CustomerControllerTest Method testFindByDescrition status {} Response {}", status, response);
		assertEquals(200, status);
	}
}
