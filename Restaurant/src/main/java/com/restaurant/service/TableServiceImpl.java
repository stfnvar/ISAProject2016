package com.restaurant.service;

import java.util.ArrayList;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restaurant.model.RestaurantSegment;
import com.restaurant.model.Table;
import com.restaurant.repository.RestaurantSegmentRepository;
import com.restaurant.repository.TableRepository;

@Service
public class TableServiceImpl implements TableService {

	@Autowired
	TableRepository tableRepository;
	
	@Autowired
	RestaurantSegmentRepository restaurantSegment;
	
	@Override
	public ArrayList<Table> findAllTables(Long id) {
		// TODO Auto-generated method stub
		return tableRepository.findAll(id);
	}

	@Override
	public Table findOneTable(Long id) {
		// TODO Auto-generated method stub
		return tableRepository.findOne(id);
	}

	@Override
	public ArrayList<Table> findTablesInRestaurantSegments(Long id) {
		// TODO Auto-generated method stub
		return tableRepository.findTablesInRestaurantSegments(id);
	}
	@Override
	public ArrayList<RestaurantSegment> findRestaurantSegmentsInRestaurant(Long id){
		return restaurantSegment.findRestaurantSegmentsInRestaurant(id);
	}

	@Override
	public ArrayList<RestaurantSegment> findRestaurantSegmentsByWorker(Long id) {
		// TODO Auto-generated method stub
		return restaurantSegment.findRestaurantSegmentsByWorker(id);
	}

}
