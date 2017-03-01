package com.restaurant.service;

import java.util.List;
import java.util.Set;

import com.restaurant.model.Grocery;

public interface GroceryService {
	void removeGrocery(Long id);
	Grocery addGrocery(Grocery g);
	List<Grocery> getAllGroceries();
}
