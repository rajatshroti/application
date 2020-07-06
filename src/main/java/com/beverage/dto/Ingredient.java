package com.beverage.dto;

import java.math.BigDecimal;

public class Ingredient {

	private String name;
	private BigDecimal price;
	
	public Ingredient(String name, BigDecimal price) {
		super();
		this.name = name;
		this.price = price;
	}

	public String getName() {
		return name;
	}
	
	public BigDecimal getPrice() {
		return price;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.toUpperCase().hashCode());
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
		Ingredient other = (Ingredient) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.toUpperCase().equals(other.name.toUpperCase()))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Ingredient [name=" + name + ", price=" + price + "]";
	}

}
