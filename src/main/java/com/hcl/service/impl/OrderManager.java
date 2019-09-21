package com.hcl.service.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.hcl.exception.CustomException;
import com.hcl.model.CustomisedSalesOrder;
import com.hcl.model.SalesOrder;
import com.hcl.repository.OrderRepository;
import com.hcl.service.OrderService;

/**
 * Class responsible to implements business logic specified in contract of Order
 * Service
 * 
 * @author Anuj Saraswat
 *
 */
@Service
public class OrderManager implements OrderService {

	@Autowired
	OrderRepository orderRepository;

	@Override
	public List<SalesOrder> createOrder(List<SalesOrder> orders) {
		List<SalesOrder> createdSalesOrders = new ArrayList<>();

		Iterator<SalesOrder> orderItr = orders.iterator();
		while (orderItr.hasNext()) {
			SalesOrder order = orderItr.next();
			createdSalesOrders.add(orderRepository.save(order));
		}

		return createdSalesOrders;
	}

	@Override
	public List<SalesOrder> getAllOrders() {
		List<SalesOrder> orders = (List<SalesOrder>) orderRepository.findAll();

		return orders;
	}

	@Override
	public void cancelOrder(Integer id) throws CustomException {
		if (id == null) {
			String errorMessage = "Order Id can not be NULL";
			throw new CustomException(HttpStatus.NOT_FOUND.value(), errorMessage);
		}
		orderRepository.delete(id);
		System.out.println("Order deleted successfully");
	}

	@Override
	public List<CustomisedSalesOrder> getOrderSummary() {
		List<SalesOrder> orders = (List<SalesOrder>) orderRepository.findAll();

		Map<String, SalesOrder> map = new HashMap<>();

		Iterator<SalesOrder> itr = orders.iterator();

		while (itr.hasNext()) {
			SalesOrder salesOrder = itr.next();
			String key = salesOrder.getOrderType().name() + "_" + salesOrder.getPricePerKg();

			if (map.get(key) == null) {
				map.put(key, salesOrder);
			} else {
				SalesOrder so = map.get(key);
				if (so.getOrderType() == salesOrder.getOrderType()) {
					so.setOrderQuantity(so.getOrderQuantity() + salesOrder.getOrderQuantity());
				}
			}
		}

		return map.values().stream()
				.sorted(Comparator.comparing(SalesOrder::getOrderType).thenComparing(SalesOrder::getPricePerKg))
				.map(this::mapToCustomisedSalesOrder)
				.collect(Collectors.toList());
				
//		List<SalesOrder> list = map.values().stream()
//				.sorted(Comparator.comparing(SalesOrder::getOrderType).thenComparing(SalesOrder::getPricePerKg))
//				.collect(Collectors.toList());
//
//		return list.stream().map(this::mapToCustomisedSalesOrder).collect(Collectors.toList());
	}

	private CustomisedSalesOrder mapToCustomisedSalesOrder(SalesOrder salesOrder) {
		return new CustomisedSalesOrder(salesOrder.getOrderQuantity(), salesOrder.getPricePerKg());
	}

	@Override
	public SalesOrder getOrder(int id) throws CustomException {
		SalesOrder salesOrder = orderRepository.findOne(Integer.valueOf(id));

		if (salesOrder == null) {
			String errorMessage = "Order with id " + id + " does not exists.";
			throw new CustomException(HttpStatus.NOT_FOUND.value(), errorMessage);
		}
		return orderRepository.findOne(Integer.valueOf(id));
	}

	@Override
	public void cancelAllOrders() {
		orderRepository.deleteAll();

	}

}
