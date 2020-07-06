package com.beverage.service;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.beverage.dto.Ingredient;
import com.beverage.dto.Item;
import com.beverage.exception.InvalidOrderException;
import com.beverage.service.CustomerService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class CustomarServiceTest {

	@Rule
	public ExpectedException expectedEx = ExpectedException.none();

	@Autowired
	private CustomerService customerService;

	@Test
	public void checkPriceWithOneIngredientExclude() {
		assertEquals("4.5", customerService.calculateOrderCost("Coffee, -sugar").toString());
	}

	@Test
	public void checkPriceWithTwoIngredientsExclude() {
		assertEquals("4.0", customerService.calculateOrderCost("Coffee, -sugar, -water").toString());
	}

	@Test
	public void checkPriceWithTwoOrMoreItems() {
		assertEquals("14.0",
				customerService.calculateOrderCost("Coffee, -sugar", "Chai, -water", "Banana Smoothie").toString());
	}

	@Test
	public void checkPriceWithOutIngredientExclude() {
		assertEquals("5", customerService.calculateOrderCost("Coffee").toString());
	}

	@Test
	public void checkWithAllIngredientExclude() {
		expectedEx.expect(InvalidOrderException.class);
		expectedEx.expectMessage("Please include atleast one ingredient.");
		customerService.calculateOrderCost("Coffee, -sugar, -milk, -water");
		throw new InvalidOrderException("Please include atleast one ingredient.");
	}

	@Test
	public void checkWithNoOrder() {
		expectedEx.expect(InvalidOrderException.class);
		expectedEx.expectMessage("Please choose atleast one item.");
		customerService.calculateOrderCost("");
		throw new InvalidOrderException("Please choose atleast one item.");
	}

	@Test
	public void checkWithWrongOrder() {
		expectedEx.expect(InvalidOrderException.class);
		expectedEx.expectMessage("fgdfgd is a invalid item.");
		customerService.calculateOrderCost("fgdfgd, -sugar");
		throw new InvalidOrderException("fgdfgd is a invalid item.");
	}

	@Before
	public void setTestData() {
		Ingredient milk = new Ingredient("MILK", new BigDecimal(1.0));
		Ingredient sugar = new Ingredient("SUGAR", new BigDecimal(0.5));
		Ingredient soda = new Ingredient("SODA", new BigDecimal(0.5));
		Ingredient mint = new Ingredient("MINT", new BigDecimal(0.5));
		Ingredient water = new Ingredient("WATER", new BigDecimal(0.5));
		customerService.setIngredients(new HashSet<>(Arrays.asList(milk, sugar, soda, mint, water)));
		Item coffee = new Item("Coffee", new HashSet<String>(Arrays.asList("Coffee", "milk", "sugar", "water")),
				new BigDecimal(5.0));
		Item chai = new Item("Chai", new HashSet<String>(Arrays.asList("Tea", "milk", "sugar", "water")),
				new BigDecimal(4.0));
		Item bananaSmoothie = new Item("Banana Smoothie",
				new HashSet<String>(Arrays.asList("Banana", "milk", "sugar", "water")), new BigDecimal(6.0));
		customerService.addItems(new HashSet<>(Arrays.asList(coffee, chai, bananaSmoothie)));

	}
}