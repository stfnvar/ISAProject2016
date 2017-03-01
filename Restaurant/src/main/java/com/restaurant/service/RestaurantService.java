package com.restaurant.service;
import java.util.List;

import com.restaurant.model.Restaurant;
import com.restaurant.service.Restaurants;
public interface RestaurantService {

	
	List<Restaurant> getAllRestaurants();
	
	Restaurant findOne(Long id);
	
	List<Restaurant> getForHistory(long id);
}
