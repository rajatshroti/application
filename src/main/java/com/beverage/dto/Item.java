package com.beverage.dto;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

public class Item {

	private String itemName;
	private Set<String> ingredients;
	private BigDecimal price;
	
	public Item(String itemName, Set<String> ingredients, BigDecimal price) {
		super();
		this.itemName = itemName;
		this.ingredients = ingredients;
		this.price = price;
	}

	public String getItemName() {
		return itemName;
	}

	public Set<String> getIngredients() {
		return new LinkedHashSet<>(ingredients);
	}

	public BigDecimal getPrice() {
		return price;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ingredients == null) ? 0 : ingredients.hashCode());
		result = prime * result + ((itemName == null) ? 0 : itemName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		if (ingredients == null) {
			if (other.ingredients != null)
				return false;
		} else if (!ingredients.equals(other.ingredients))
			return false;
		if (itemName == null) {
			if (other.itemName != null)
				return false;
		} else if (!itemName.toUpperCase().equals(other.itemName.toUpperCase()))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Item [itemName=" + itemName + ", ingredients=" + ingredients + ", price=" + price + "]";
	}
}	
	
	
	
