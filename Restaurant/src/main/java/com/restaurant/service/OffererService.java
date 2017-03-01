package com.restaurant.service;

import java.util.List;
import java.util.Set;

import com.restaurant.model.Offerer;
import com.restaurant.model.Person;

public interface OffererService {
	void removeOfferer(Long id);
	void addOfferer(Offerer o);
	List<Offerer> getOfferersByRestaurantId(Long id); 
	List<Offerer> getOfferers();
	Offerer findOne(Long id);
	Person update(String name, String surname, Long id);
	Person changePassword(Long id, String password);
	void changeFirstTime(boolean b, Long id);
}
