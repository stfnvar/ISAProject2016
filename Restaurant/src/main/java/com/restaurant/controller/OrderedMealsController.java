package com.restaurant.controller;


import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.restaurant.model.Bartender;
import com.restaurant.model.Cook;
import com.restaurant.model.OrderedDrink;
import com.restaurant.model.OrderedMeal;
import com.restaurant.service.OrderedMealServiceImpl;


@RestController
@RequestMapping("/orderedMeals")
public class OrderedMealsController {

	@Autowired
	OrderedMealServiceImpl orderedMealServiceImpl;
	
	
	@RequestMapping(value = "/getAll", method = RequestMethod.GET)
	public ArrayList<OrderedMeal> getAllOrderedMeals(HttpServletRequest req){
		if(req.getSession().getAttribute("guest") instanceof Cook){
			
			Cook cook = (Cook) req.getSession().getAttribute("guest");
			
			return orderedMealServiceImpl.findAllOrderedMeals(cook.getRestaurant().getId());
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
	
	
}