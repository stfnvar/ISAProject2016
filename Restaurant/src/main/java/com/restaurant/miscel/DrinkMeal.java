package com.restaurant.miscel;

import java.util.ArrayList;

public class DrinkMeal {
	
	private ArrayList<String> drinks;
	private ArrayList<String> meals;
	private long tableId;
	
	private ArrayList<Integer> quantityDrinks;
	private ArrayList<Integer> quantityMeals;	

	public ArrayList<Integer> getQuantityDrinks() {
		return quantityDrinks;
	}
	public void setQuantityDrinks(ArrayList<Integer> quantityDrinks) {
		this.quantityDrinks = quantityDrinks;
	}
	public ArrayList<Integer> getQuantityMeals() {
		return quantityMeals;
	}
	public void setQuantityMeals(ArrayList<Integer> quantityMeals) {
		this.quantityMeals = quantityMeals;
	}
	public ArrayList<String> getDrinks() {
		return drinks;
	}
	public void setDrinks(ArrayList<String> drinks) {
		this.drinks = drinks;
	}
	public ArrayList<String> getMeals() {
		return meals;
	}
	public void setMeals(ArrayList<String> meals) {
		this.meals = meals;
	}
	public long getTableId() {
		return tableId;
	}
	public void setTableId(long tableId) {
		this.tableId = tableId;
	}
	public DrinkMeal() {
		super();
	}
	
	

}
