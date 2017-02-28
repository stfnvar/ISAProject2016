package com.restaurant.service;

import java.util.ArrayList;

import com.restaurant.model.Table;

public interface TableService {

	ArrayList<Table> findAllTables(Long id);
	
	Table findOneTable(Long id);
	
}
