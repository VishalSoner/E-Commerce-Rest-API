package com.ECommerce;

import java.io.IOException;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.ecommerce.ECommerceApplication;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@AutoConfigureMockMvc
@ContextConfiguration
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ECommerceApplication.class)
public class AbstractTestsClass {

	@Autowired
	public MockMvc mvc;

	protected String convertObjectToJson(Object eCommerce) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(eCommerce);
	}

	public static <T> T convertJsonToObject(String jsonString, Class<T> targetType) throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.readValue(jsonString, targetType);
	}
}
