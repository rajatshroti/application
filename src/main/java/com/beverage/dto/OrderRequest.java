package com.beverage.dto;

import java.util.Set;

public class OrderRequest {

	private Set<Order> orders;

	public OrderRequest(Set<Order> orders) {
		super();
		this.orders = orders;
	}

	public Set<Order> getOrders() {
		return orders;
	}

	@Override
	public String toString() {
		return "OrderRequest [orders=" + orders + "]";
	}

}
