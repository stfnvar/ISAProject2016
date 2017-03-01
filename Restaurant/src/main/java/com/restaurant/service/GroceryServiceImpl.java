package com.restaurant.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restaurant.model.Grocery;
import com.restaurant.repository.GroceryRepository;
@Service
public class GroceryServiceImpl implements GroceryService{

	@Autowired
	GroceryRepository groceryRepository;
	
	@Override
	public void removeGrocery(Long id) {
		groceryRepository.delete(id);
	}

	@Override
	public Grocery addGrocery(Grocery g) {
		return groceryRepository.save(g);
	}

	@Override
	public List<Grocery> getAllGroceries() {
		return groceryRepository.findAll();
	}

}
