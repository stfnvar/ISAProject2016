package com.restaurant.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restaurant.model.Restaurant;
import com.restaurant.repository.RestaurantRepository;
@Service
public class RestaurantServiceImpl implements RestaurantService{

	@Autowired
	RestaurantRepository restaurantRepository;
	
	
	@Override
	public List<Restaurant> getAllRestaurants() {
	
		return restaurantRepository.findAll();
	}


	@Override
	public Restaurant findOne(Long id) {
		// TODO Auto-generated method stub
		return restaurantRepository.findOne(id);
	}
	
	

}
