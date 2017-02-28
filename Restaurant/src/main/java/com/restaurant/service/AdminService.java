package com.restaurant.service;

import java.util.Set;

import com.restaurant.model.Administrator;
import com.restaurant.model.Restaurant;
import com.restaurant.model.RestaurantManager;

public interface AdminService {

	Administrator findOneById(long id);

	void addRestaurant(Restaurant r);

	void removeRestaurant(Long id);

	Set<RestaurantManager> getRestaurantManagerByRestaurantId(Long id);
}
