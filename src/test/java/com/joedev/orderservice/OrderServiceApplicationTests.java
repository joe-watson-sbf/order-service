package com.joedev.orderservice;

import com.joedev.orderservice.controller.ClientController;
import com.joedev.orderservice.controller.OrderController;
import com.joedev.orderservice.controller.ProductController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class OrderServiceApplicationTests {

	/*
	* A simple sanity check test that will fail if the application context cannot start
	* */

	@Autowired
	private ClientController clientController;
	@Autowired
	private ProductController productController;
	@Autowired
	private OrderController orderController;

	@Test
	@DisplayName("SANITY TEST")
	void contextLoads() {
		assertThat(clientController).isNotNull();
		assertThat(productController).isNotNull();
		assertThat(orderController).isNotNull();
	}
}
