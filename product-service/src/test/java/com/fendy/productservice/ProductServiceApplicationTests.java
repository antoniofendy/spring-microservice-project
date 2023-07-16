package com.fendy.productservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fendy.productservice.dto.ProductRequest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
@Slf4j
class ProductServiceApplicationTests {

//	@Container
//	static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:6.0.6")
//			.withEnv("MONGODB_URI", "mongodb+srv://layfenfen:Pass_220901@cluster0.zvcc25a.mongodb.net/microservice");

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

//	@DynamicPropertySource
//	static void setProperties(DynamicPropertyRegistry dynamicPropertyRegistry) {
//		dynamicPropertyRegistry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
//	}

	@Test
	void testCreateProduct() throws Exception {

		ProductRequest productRequest = getProductRequest();
		String productRequestString = objectMapper.writeValueAsString(productRequest);
		mockMvc.perform(post("/api/v1/product")
				.contentType(MediaType.APPLICATION_JSON)
				.content(productRequestString)
		).andExpectAll(
				status().isCreated()
		);

	}

	private ProductRequest getProductRequest() {
		return ProductRequest.builder()
				.name("Iphone 13 test")
				.description("Iphone 13 desc test")
				.price(BigDecimal.valueOf(1200))
				.build();
	}

}
