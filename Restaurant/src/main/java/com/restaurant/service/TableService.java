package com.restaurant.service;

import java.util.ArrayList;
import java.util.Set;

import org.springframework.data.jpa.repository.Query;

import com.restaurant.model.RestaurantSegment;
import com.restaurant.model.Table;

public interface TableService {

	ArrayList<Table> findAllTables(Long id);
	
	Table findOneTable(Long id);
	
	ArrayList<RestaurantSegment> findRestaurantSegmentsInRestaurant(Long id);
	
	ArrayList<Table> findTablesInRestaurantSegments(Long id);

	ArrayList<RestaurantSegment> findRestaurantSegmentsByWorker(Long id);
	
}
