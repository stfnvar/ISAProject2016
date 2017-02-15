package com.restaurant.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.restaurant.model.Drink;
import com.restaurant.model.DrinkCard;
import com.restaurant.model.Meal;
import com.restaurant.model.Menu;
import com.restaurant.model.Restaurant;
import com.restaurant.model.RestaurantSegment;
import com.restaurant.service.RestaurantManagerServiceImpl;

@RestController
@RequestMapping("/restaurantManager")
public class RestaurantManagerController {

	@Autowired
	private RestaurantManagerServiceImpl restaurantManagerServiceImpl;
	
	
	@RequestMapping(method = RequestMethod.GET, value = "/restaurantProfile")
	@ResponseBody
	public Restaurant getRestaurantProfile(){
		Long id = 1L;
		return restaurantManagerServiceImpl.getRestaurantProfile(id);
	}
	
	
	@RequestMapping(method = RequestMethod.GET, value = "/restaurantSegments")
	@ResponseBody
	public Set<RestaurantSegment> getRestaurantSegments(){
		Long id = 1L;
		return restaurantManagerServiceImpl.getRestaurantSegments(id);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/menu")
	@ResponseBody
	public Menu getMenu(){
		Long id = 1L;
		return restaurantManagerServiceImpl.getRestaurantMenu(id);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/menuMeals")
	@ResponseBody
	public Set<Meal> getMenuMeals(){
		Long id = 1L;
		return restaurantManagerServiceImpl.getMenuMeals(id);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/drinkCard")
	@ResponseBody
	public DrinkCard getDrinkCard(){
		Long id = 1L;
		return restaurantManagerServiceImpl.getRestaurantDrinkCard(id);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/drinks")
	@ResponseBody
	public Set<Drink> getDrinkCardDrinks(){
		Long id = 1L;
		return restaurantManagerServiceImpl.getDrinkCardDrinks(id);
	}
	
}
