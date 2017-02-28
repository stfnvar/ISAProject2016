package com.restaurant.service;

import java.util.ArrayList;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restaurant.model.Drink;
import com.restaurant.model.DrinkCard;
import com.restaurant.model.Meal;
import com.restaurant.model.Menu;
import com.restaurant.model.Restaurant;
import com.restaurant.model.RestaurantManager;
import com.restaurant.model.RestaurantSegment;
import com.restaurant.model.Worker;
import com.restaurant.repository.DrinkCardRepository;
import com.restaurant.repository.DrinkRepository;
import com.restaurant.repository.MealRepository;
import com.restaurant.repository.MenuRepository;
import com.restaurant.repository.RestaurantManagerRepository;
import com.restaurant.repository.RestaurantRepository;
import com.restaurant.repository.RestaurantSegmentRepository;

@Service
public class RestaurantManagerServiceImpl implements RestaurantManagerService {

	@Autowired
	RestaurantRepository restaurantRepository;
	
	@Autowired
	RestaurantManagerRepository restaurantManagerRepository;
	
	@Autowired
	RestaurantSegmentRepository restaurantSegmentRepository;
	
	@Autowired
	MenuRepository menuRepository;
	
	@Autowired
	MealRepository mealRepository;
	
	@Autowired
	DrinkCardRepository drinkCardRepository;
	
	@Autowired
	DrinkRepository drinkRepository;
	
	@Override
	public Restaurant getRestaurantProfile(Long id) {
		Restaurant restaurant = (Restaurant) restaurantRepository.findOne(id);
		return restaurant;
	}

	@Override
	public ArrayList<Worker> getWorkers() {
		return null;
	}

	@Override
	public Set<RestaurantSegment> getRestaurantSegments(Long id) {
		return restaurantSegmentRepository.findByRestaurant_Id(id);
	}

	@Override
	public Menu getRestaurantMenu(Long id) {
		// TODO Auto-generated method stub
		return menuRepository.findByRestaurant_Id(id);
	}

	@Override
	public Set<Meal> getMenuMeals(Long id) {
		// TODO Auto-generated method stub
		return mealRepository.findByMenu_Id(id);
	}

	@Override
	public DrinkCard getRestaurantDrinkCard(Long id) {
		// TODO Auto-generated method stub
		return drinkCardRepository.findByRestaurant_Id(id);
	}

	@Override
	public Set<Drink> getDrinkCardDrinks(Long id) {
		// TODO Auto-generated method stub
		return drinkRepository.findByDrinkCard_Id(id);
	}

	@Override
	public RestaurantManager findOneById(long id) {
		
		return restaurantManagerRepository.findById(id);
	}
	
}
