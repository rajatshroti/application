package com.beverage.dto;

import java.util.ArrayList;
import java.util.List;

public class Order {

	String itemName;
	
	List<String> excludes = new ArrayList<>();

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public List<String> getExcludes() {
		return excludes;
	}
	
}
