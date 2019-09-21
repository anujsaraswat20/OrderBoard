package com.hcl.service;

import java.util.List;

import com.hcl.exception.CustomException;
import com.hcl.model.CustomisedSalesOrder;
import com.hcl.model.SalesOrder;

/**
 * Contract of Order Service
 * 
 * @author Anuj Saraswat
 *
 */
public interface OrderService {

	/**
	 * Create order in DB
	 * 
	 * @param orders List of sales order to be created
	 */
	List<SalesOrder> createOrder(List<SalesOrder> orders);

	/**
	 * Returns list of all Sales orders
	 * 
	 * @return Returns list of all Sales orders
	 */
	List<SalesOrder> getAllOrders();

	/**
	 * Returns list of summary of all Sales orders
	 * 
	 * @return Returns list of summary of all Sales orders
	 */
	List<CustomisedSalesOrder> getOrderSummary();

	/**
	 * Delete order mapped with id
	 * 
	 * @param id Id of sales order to be deleted
	 * @throws CustomException exception with necessary reason
	 */
	void cancelOrder(Integer id) throws CustomException;

	/**
	 * Delete all orders
	 */
	void cancelAllOrders();
	
	/**
	 * Return sales order associated to id
	 * 
	 * @param id Sales order id
	 * @return Return sales order associated to id
	 * @throws CustomException exception with necessary reason
	 */
	SalesOrder getOrder(int id) throws CustomException;
}
