package com.restaurant.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.restaurant.model.Table;

public interface TableService {

	ArrayList<Table> findAllTables(Long id);
	
	Table findOneTable(Long id);
	

	Set<Table> getTablesByRestId(long id);
	
	List<Integer> getBannedTables(long id, String start, String end);
	
}
