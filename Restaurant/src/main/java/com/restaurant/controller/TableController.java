package com.restaurant.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.restaurant.model.Table;
import com.restaurant.model.Waiter;
import com.restaurant.service.TableServiceImpl;

@RestController
@RequestMapping("/tables")
public class TableController {
	
	@Autowired
	TableServiceImpl tableServiceImpl;
	
	@RequestMapping(value="/getAll", method = RequestMethod.GET)
	public ArrayList<Table> getAll(HttpServletRequest req){
		if(req.getSession().getAttribute("guest") instanceof Waiter){

			Waiter waiter = (Waiter) req.getSession().getAttribute("guest");
			//ArrayList<Table> t = tableServiceImpl.findAllTables(waiter.getRestaurant().getId());
			//System.out.println(t.get(0));
			return tableServiceImpl.findAllTables(waiter.getRestaurant().getId());
		} 
		return null;
	}
	
}
