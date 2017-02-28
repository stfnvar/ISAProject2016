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
import com.restaurant.model.OrderedDrink;
import com.restaurant.service.OrderedDrinkServiceImpl;

@RestController
@RequestMapping("/orderedDrinks")
public class OrderedDrinksController {

	@Autowired
	OrderedDrinkServiceImpl orderedDrinkServiceImpl;
	
	
	@RequestMapping(value = "/getAll", method = RequestMethod.GET)
	public ArrayList<OrderedDrink> getAllOrderedDrinks(HttpServletRequest req){
		if(req.getSession().getAttribute("guest") instanceof Bartender){
			Bartender bartender = (Bartender) req.getSession().getAttribute("guest");
			return orderedDrinkServiceImpl.findAllOrderedDrinks(bartender.getRestaurant().getId());
		}
		return null;
	}
	
	@Transactional
	@RequestMapping(value = "/updateReady/{id}", method = RequestMethod.POST)
	public OrderedDrink drinkIsReady(@PathVariable long id){
		OrderedDrink od = orderedDrinkServiceImpl.findOneOrderedDrink(id);
		System.out.println(id);
		System.out.println(od.getReady());
		orderedDrinkServiceImpl.updateReady(id);

		System.out.println(od.getReady());
		return od;
	}
	
}
