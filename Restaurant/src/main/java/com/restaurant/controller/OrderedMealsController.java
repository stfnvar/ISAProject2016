package com.restaurant.controller;


import java.util.ArrayList;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.restaurant.model.Bartender;
import com.restaurant.model.Cook;
import com.restaurant.model.Drink;
import com.restaurant.model.Meal;
import com.restaurant.model.Order;
import com.restaurant.model.OrderedDrink;
import com.restaurant.model.OrderedMeal;
import com.restaurant.model.Waiter;
import com.restaurant.service.MealServiceImpl;
import com.restaurant.service.OrderedMealServiceImpl;


@RestController
@RequestMapping("/orderedMeals")
public class OrderedMealsController {

	@Autowired
	OrderedMealServiceImpl orderedMealServiceImpl;
	
	@Autowired
	MealServiceImpl mealServiceImpl;
	
	@RequestMapping(value = "/getMealsRestaurant", method = RequestMethod.GET)
	public Set<Meal> getDrinksRestaurant(HttpServletRequest req){
		if(req.getSession().getAttribute("guest") instanceof Waiter){
			Waiter waiter = (Waiter) req.getSession().getAttribute("guest");
			return mealServiceImpl.findByRestaurant(waiter.getRestaurant().getId());
		}
		return null;
	}
	
	
	@RequestMapping(value = "/getAll", method = RequestMethod.GET)
	public ArrayList<OrderedMeal> getAllOrderedMeals(HttpServletRequest req){
		if(req.getSession().getAttribute("guest") instanceof Cook){
			
			Cook cook = (Cook) req.getSession().getAttribute("guest");
			
			return orderedMealServiceImpl.findAllOrderedMeals(cook.getRestaurant().getId(), cook.getTypeC());
		}
		return null;
	}
	
	
	@Transactional
	@RequestMapping(value = "/acceptMeal/{id}", method = RequestMethod.POST)
	public OrderedMeal acceptMeal(@PathVariable long id){
		OrderedMeal om = orderedMealServiceImpl.findOneOrderedMeal(id);
		orderedMealServiceImpl.updateAcceptedMeal(id);

		return om;
	}
	
	@Transactional
	@RequestMapping(value = "/makeMeal/{id}", method = RequestMethod.POST)
	public OrderedMeal makeMeal(@PathVariable long id){
		OrderedMeal om = orderedMealServiceImpl.findOneOrderedMeal(id);
		orderedMealServiceImpl.updateMadeMeal(id);
		
		return om;
	}
	
	@Transactional
	@RequestMapping(value = "/addToOrder", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public OrderedMeal addToOrder(@RequestBody OrderedMeal om){
		
		om.setReady(0);
		om.setAcceptedMeal(0);
		
		orderedMealServiceImpl.save(om);
		
		return om;
	}
	
	@Transactional
	@RequestMapping(value = "/checkAcceptedMeal", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public int checkAcceptedMeal(@RequestBody Order o){
		
		ArrayList<OrderedMeal> meals = orderedMealServiceImpl.findByOrderId(o.getId());
		boolean flag = false;
		if(!meals.isEmpty()){
			for(int i = 0; i < meals.size(); i++){
				if(meals.get(i).getAcceptedMeal() == 0){
					flag = true;
				}
			}
		}
		if(flag == true)
			return 1;
		else 
			return 0;
	}
	
	
}
