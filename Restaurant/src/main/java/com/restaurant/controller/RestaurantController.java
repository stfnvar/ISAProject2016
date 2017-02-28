package com.restaurant.controller;

import java.sql.Date;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restaurant.miscel.MessageWithObj;
import com.restaurant.service.RestaurantServiceImpl;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {

	
	@Autowired
	RestaurantServiceImpl restaurantServiceImpl;
	
	
	@RequestMapping(value = "/getRestaurants")
	public MessageWithObj getAllRestaurants() {
		
		return new MessageWithObj("Svi restorani", true, restaurantServiceImpl.getAllRestaurants());
	}
	
	@RequestMapping(value = "/getAvailableDesks/{starts}/{ends}/{idrest}")
	public MessageWithObj getAvailableDesks(@PathVariable("starts") String starts,
			@PathVariable("ends") String ends, @PathVariable("idrest") String idrest){
		
		System.out.println(Long.parseLong(starts));
		System.out.println(Long.parseLong(ends));
		System.out.println(idrest);
		
		long d= Long.parseLong(starts);
		java.sql.Timestamp stamp = new java.sql.Timestamp(d);
		  Date date = new Date(stamp.getTime());
		  
		  SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		  String sql = formatter.format(date);
		  
		  System.out.println(sql);
		return new MessageWithObj("adfasdf", true, null);
	}
	
	
	
}
