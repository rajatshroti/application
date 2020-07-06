package com.beverage.dao;

import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Repository;

import com.beverage.dto.Ingredient;
import com.beverage.dto.Item;

@Repository
public class CustomarDaoImpl implements CustomerDao{
	
	private ConcurrentHashMap<String, Item> menuItems = new ConcurrentHashMap<>();
	private ConcurrentHashMap<String, Ingredient> ingredients = new ConcurrentHashMap<>();

	@Override
	public void addItem(final Item item) {
		menuItems.put(item.getItemName().toUpperCase(), item);
	}
	
	@Override
	public void setIngredient(Ingredient ingredient) {
		ingredients.put(ingredient.getName().toUpperCase(), ingredient);
	}

	@Override
	public Ingredient getIngredient(String ingredientName) {
		return ingredients.getOrDefault(ingredientName.toUpperCase(), null);
	}

	@Override
	public Item getItem(String itemName) {
		return menuItems.getOrDefault(itemName.toUpperCase(), null);
	}
	
}
