package com.beverage.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.beverage.dao.CustomerDao;
import com.beverage.dto.Ingredient;
import com.beverage.dto.Item;
import com.beverage.dto.Order;
import com.beverage.dto.OrderRequest;
import com.beverage.exception.InvalidOrderException;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerDao customerDao;

	@Override
	public void addItems(Set<Item> items) {
		items.stream().forEach(item -> {
			customerDao.addItem(item);
		});

	}

	@Override
	public void setIngredients(Set<Ingredient> ingredients) {
		ingredients.stream().forEach(ingredient -> {
			customerDao.setIngredient(ingredient);
		});
	}

	@Override
	public BigDecimal calculateOrderCost(final String... orders) {

		OrderRequest orderRequest = parseRequest(orders);
		if (orderRequest.getOrders().size() < 1)
			throw new InvalidOrderException("Please choose atleast one item.");
		BigDecimal totalCost = BigDecimal.ZERO;
		for (Order order : orderRequest.getOrders()) {
			Item item = customerDao.getItem(order.getItemName());
			if (null == item)
				throw new InvalidOrderException(order.getItemName() + " is a invalid item.");
			BigDecimal itemCost = item.getPrice();
			if (item.getIngredients().size() - 1 <= order.getExcludes().size()) {
				throw new InvalidOrderException("Please include atleast one ingredient.");
			} else {
				for (String ingredient : order.getExcludes()) {
					itemCost = itemCost.subtract(customerDao.getIngredient(ingredient).getPrice());
				}
			}
			totalCost = totalCost.add(itemCost);
		}

		return totalCost;
	}

	private OrderRequest parseRequest(final String... orders) {
		Set<Order> orderReqList = new HashSet<>();
		Arrays.stream(orders).forEach(order -> {
			String[] orderArgArr = order.split(",");
			Order orderReq = new Order();
			orderReq.setItemName(orderArgArr[0]);
			if (orderArgArr.length > 1) {
				for (int index = 1; index < orderArgArr.length; index++) {
					orderReq.getExcludes().add(orderArgArr[index].trim().substring(1).toUpperCase());
				}
			}
			if (orderReq.getItemName() != null && !orderReq.getItemName().trim().equals(""))
				orderReqList.add(orderReq);
		});
		return new OrderRequest(orderReqList);
	}

}
