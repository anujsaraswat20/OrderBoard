package com.hcl.model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Model class for Order
 * 
 * @author Anuj Saraswat
 *
 */

@Entity
public class SalesOrder {

	@Id
	private Integer id;

	private String userid;
	private Double orderQuantity;
	private Double pricePerKg;
	private OrderType orderType;

	/**
	 * Constructor
	 * 
	 * @param id            Id of sales order
	 * @param userid        User Id associated to sales order
	 * @param orderQuantity Quantity of an order in KG
	 * @param pricePerKg    Price per KG in Dollar
	 * @param orderType     Type of order like SELL and BUY
	 */
	public SalesOrder(Integer id, String userid, Double orderQuantity, Double pricePerKg, OrderType orderType) {
		super();
		this.id = id;
		this.userid = userid;
		this.orderQuantity = orderQuantity;
		this.pricePerKg = pricePerKg;
		this.orderType = orderType;
	}

	/**
	 * Default constructor
	 */
	public SalesOrder() {

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
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

	public OrderType getOrderType() {
		return orderType;
	}

	public void setOrderType(OrderType orderType) {
		this.orderType = orderType;
	}

	@Override
	public String toString() {
		return "SalesOrder [id=" + id + ", userid=" + userid + ", orderQuantity=" + orderQuantity + ", pricePerKg="
				+ pricePerKg + ", orderType=" + orderType + "]";
	}
	
	
}
