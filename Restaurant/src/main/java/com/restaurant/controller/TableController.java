package com.restaurant.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.restaurant.model.RestaurantSegment;
import com.restaurant.model.Table;
import com.restaurant.model.Waiter;
import com.restaurant.service.TableServiceImpl;

@RestController
@RequestMapping("/tables")
public class TableController {
	
	@Autowired
	TableServiceImpl tableServiceImpl;
	
	@RequestMapping(value = "/getAllAndWaitersTables")
	public HashSet<Table> getAllAndWaitersTables(HttpServletRequest req){
		if(req.getSession().getAttribute("guest") instanceof Waiter){
			Waiter waiter = (Waiter) req.getSession().getAttribute("guest");

			ArrayList<RestaurantSegment> restaurantSegments = tableServiceImpl.findRestaurantSegmentsInRestaurant(waiter.getRestaurant().getId());
			System.out.println("SVISEGMENTI"+restaurantSegments.size());
			ArrayList<RestaurantSegment> restaurantSegmentsWorker = new ArrayList<>();
			
			for(int i = 0; i < restaurantSegments.size(); i++){
				restaurantSegmentsWorker.addAll(tableServiceImpl.findRestaurantSegmentsByWorker(waiter.getId()));
			}	
			System.out.println("SEGMENTIODRADNIKA"+restaurantSegmentsWorker.size());
			ArrayList<Table> tables = new ArrayList<>();
			for(int i = 0; i < restaurantSegmentsWorker.size(); i++){
				tables.addAll(tableServiceImpl.findTablesInRestaurantSegments(restaurantSegmentsWorker.get(i).getId()));
			}	
			System.out.println("STOLOVI"+tables.size());
			
			HashSet<Table> hes = new HashSet<>();
			for(int i = 0; i < tables.size(); i++){
				hes.add(tables.get(i));
			}
			
			return hes;
			//return tableServiceImpl.findAllTables(waiter.getRestaurant().getId());
		} 
		return null;
	}
	
	@RequestMapping(value="/getAll", method = RequestMethod.GET)
	public ArrayList<Table> getAll(HttpServletRequest req){
		if(req.getSession().getAttribute("guest") instanceof Waiter){

			Waiter waiter = (Waiter) req.getSession().getAttribute("guest");
			ArrayList<Table> t = tableServiceImpl.findAllTables(waiter.getRestaurant().getId());
			System.out.println("SVISTOLOVI" + t.size());
			return tableServiceImpl.findAllTables(waiter.getRestaurant().getId());
		} 
		return null;
	}
	
}
