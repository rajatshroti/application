package com.beverage.service;

import java.math.BigDecimal;
import java.util.Set;

import com.beverage.dto.Ingredient;
import com.beverage.dto.Item;

public interface CustomerService {
	
	void addItems(Set<Item> items);

	void setIngredients(Set<Ingredient> ingredients);
	
	BigDecimal calculateOrderCost(String... orders);

}
