package com.beverage.dao;


import java.util.Map;

import com.beverage.dto.Ingredient;
import com.beverage.dto.Item;

public interface CustomerDao {
	
	void addItem(Item item);
	
	Item getItem(String itemName);
	
	void setIngredient(Ingredient ingredient);
	
	Ingredient getIngredient(String ingredientName);
	
}
