package com.restaurant.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restaurant.model.OrderedDrink;
import com.restaurant.repository.OrderedDrinkRepository;

@Service
public class OrderedDrinkServiceImpl implements OrderedDrinkService {
	
	@Autowired
	OrderedDrinkRepository orderedDrinkRepository;

	@Override
	public ArrayList<OrderedDrink> findAllOrderedDrinks(Long id) {
		// TODO Auto-generated method stub
		return orderedDrinkRepository.findAll(id);
	}

	@Override
	public void updateReady(long id) {
		// TODO Auto-generated method stub
		orderedDrinkRepository.updateReady(id);
	}

	@Override
	public OrderedDrink findOneOrderedDrink(long id) {
		// TODO Auto-generated method stub
		return orderedDrinkRepository.findOne(id);
	}

	@Override
	public OrderedDrink save(OrderedDrink orderedDrink) {
		// TODO Auto-generated method stub
		return orderedDrinkRepository.save(orderedDrink);
	}

	@Override
	public ArrayList<OrderedDrink> findByOrderId(Long id) {
		// TODO Auto-generated method stub
		return orderedDrinkRepository.findByOrderId(id);
	}

	@Override
	public void updateQuantity(int quantity, long id) {
		// TODO Auto-generated method stub
		orderedDrinkRepository.updateQuantity(quantity, id);
	}

	@Override
	public void updateDrinkName(long idDrink, long id) {
		// TODO Auto-generated method stub
		orderedDrinkRepository.updateDrinkName(idDrink, id);
	}


}
