package com.restaurant.controller;

import java.sql.Date;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restaurant.miscel.MessageWithObj;
import com.restaurant.service.RestaurantServiceImpl;
import com.restaurant.service.TableServiceImpl;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {

	
	@Autowired
	RestaurantServiceImpl restaurantServiceImpl;
	
	@Autowired
	TableServiceImpl tableServiceImpl;
	
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
		
		long idresta = Long.parseLong(idrest);
		
		long d= Long.parseLong(starts);
		java.sql.Timestamp stamp = new java.sql.Timestamp(d);
		  Date date = new Date(stamp.getTime());
		  
		  SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		  String sqlstart = formatter.format(date);
		  
		  d= Long.parseLong(ends);
		  stamp = new java.sql.Timestamp(d);
		  date = new Date(stamp.getTime());
		  formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		  String sqlend = formatter.format(date);
		  
		  System.out.println(sqlstart);
		  System.out.println(sqlend);
		  
		 
		return new MessageWithObj("banned tables", true, tableServiceImpl.getBannedTables(idresta, sqlstart, sqlend));
	}
	
	@RequestMapping(value = "/getAllDesks/{idrest}")
	public MessageWithObj getAllDesksForRest(@PathVariable("idrest") String idrest){
		
		long id = Long.parseLong(idrest);
		
		return new MessageWithObj("stolovi", true, tableServiceImpl.getTablesByRestId(id));
	}
	
	
	
}
