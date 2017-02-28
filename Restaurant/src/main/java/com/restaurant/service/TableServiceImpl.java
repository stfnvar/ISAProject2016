package com.restaurant.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restaurant.model.Table;
import com.restaurant.repository.TableRepository;

@Service
public class TableServiceImpl implements TableService {

	@Autowired
	TableRepository tableRepository;
	
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
	public Set<Table> getTablesByRestId(long id) {
		
		return tableRepository.findByRestaurantId(id);
	}

	@Override
	public List<Integer> getBannedTables(long id, String start, String end) {
		
		return tableRepository.getWrongTables(id, start, end);
	}

}
