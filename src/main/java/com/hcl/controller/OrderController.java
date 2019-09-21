package com.hcl.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.hcl.exception.CustomException;
import com.hcl.exception.RunTimeCustomException;
import com.hcl.model.CustomisedSalesOrder;
import com.hcl.model.SalesOrder;
import com.hcl.service.OrderService;

/**
 * Controller class responsible to expose REST APIs
 * 
 * @author Anuj Saraswat
 */
@RestController
@RequestMapping("/")
public class OrderController {

	private static final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);

	@Autowired
	OrderService orderService;

	@PostMapping(value = "/order/add")
	public List<ResponseEntity<Void>> createOrder(@RequestBody List<SalesOrder> orders) {

		LOGGER.info("Enter in Create Order methiod with parameters : {}", orders);

		List<SalesOrder> createdOrders = orderService.createOrder(orders);
		List<ResponseEntity<Void>> response = new ArrayList<ResponseEntity<Void>>();

		Iterator<SalesOrder> itr = createdOrders.iterator();

		while (itr.hasNext()) {
			SalesOrder order = itr.next();
			final URI orderURI = ServletUriComponentsBuilder.fromCurrentServletMapping().path("/getorder/{orderId}")
					.build().expand(order.getId()).toUri();

			final HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.setLocation(orderURI);
			response.add(new ResponseEntity<Void>(httpHeaders, HttpStatus.CREATED));
		}

		LOGGER.info("Returning from Create Order method with response : {}", response);
		return response;

	}

	@GetMapping(value = "/getallorders")
	public List<SalesOrder> getAllOrders() {
		return orderService.getAllOrders();
	}

	@GetMapping(value = "/getordersummary")
	public List<CustomisedSalesOrder> getOrderSummary() {
		return orderService.getOrderSummary();
	}

	@GetMapping(value = "/getorder/{id}")
	public SalesOrder getOrder(@PathVariable String id) throws CustomException {
		LOGGER.info("Entered in get order method with parameter as id : {}", id);
		return orderService.getOrder(validateOrderId(id));
	}

	@DeleteMapping(value = "/cancelorder/{id}")
	public void cancelOrder(@PathVariable String id) throws CustomException {
		LOGGER.info("Entered in cencel order method with parameter as id : {}", id);
		orderService.cancelOrder(validateOrderId(id));
	}

	@DeleteMapping(value = "/cancelallorders")
	public void cancelAllOrders() {
		orderService.cancelAllOrders();
	}

	private int validateOrderId(String id) {
		int orderId = 0;
		try {
			orderId = Integer.valueOf(id);
		} catch (RuntimeException runtimeException) {
			throw new RunTimeCustomException(HttpStatus.BAD_REQUEST.value(), "No valid order Id found in input");
		}
		return orderId;
	}
}
