package com.hcl.model;

/**
 * Model class for Order
 * 
 * @author Anuj Saraswat
 *
 */

public class CustomisedSalesOrder {


	private Double orderQuantity;
	private Double pricePerKg;

	/**
	 * Constructor
	 * 
	 * @param id            Id of sales order
	 * @param userid        User Id associated to sales order
	 * @param orderQuantity Quantity of an order in KG
	 * @param pricePerKg    Price per KG in Dollar
	 * @param orderType     Type of order like SELL and BUY
	 */
	public CustomisedSalesOrder(Double orderQuantity, Double pricePerKg) {
		super();
		this.orderQuantity = orderQuantity;
		this.pricePerKg = pricePerKg;
	}

	/**
	 * Default constructor
	 */
	public CustomisedSalesOrder() {

	}

	public Double getOrderQuantity() {
		return orderQuantity;
	}

	public void setOrderQuantity(Double orderQuantity) {
		this.orderQuantity = orderQuantity;
	}

	public Double getPricePerKg() {
		return pricePerKg;
	}

	public void setPricePerKg(Double pricePerKg) {
		this.pricePerKg = pricePerKg;
	}
}
