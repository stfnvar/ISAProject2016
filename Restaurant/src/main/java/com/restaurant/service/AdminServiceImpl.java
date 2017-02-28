package com.restaurant.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restaurant.model.Administrator;
import com.restaurant.model.Restaurant;
import com.restaurant.model.RestaurantManager;
import com.restaurant.repository.AdminRepository;
import com.restaurant.repository.RestaurantManagerRepository;
import com.restaurant.repository.RestaurantRepository;

@Service
public class AdminServiceImpl implements AdminService {

	
	@Autowired
	AdminRepository adminRepo;
	
	@Autowired
	RestaurantRepository restaurantRepository;
	
	@Autowired
	RestaurantManagerRepository restaurantManagerRepository;
	
	@Override
	public Administrator findOneById(long id) {
		
		return adminRepo.findById(id);
	}

	@Override
	public void addRestaurant(Restaurant r) {
		restaurantRepository.save(r);
	}

	@Override
	public void removeRestaurant(Long id) {
		restaurantRepository.delete(id);
	}

	@Override
	public Set<RestaurantManager> getRestaurantManagerByRestaurantId(Long id) {
		// TODO Auto-generated method stub
		return restaurantManagerRepository.getRestaurantManagersByRestaurantId(id);
	}
	
	
}
