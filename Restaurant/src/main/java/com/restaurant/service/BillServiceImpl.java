package com.restaurant.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restaurant.model.Bill;
import com.restaurant.repository.BillRepository;

@Service
public class BillServiceImpl implements BillService {

	@Autowired
	BillRepository billRepository;
	
	@Override
	public void save(Bill bill) {
		// TODO Auto-generated method stub
		billRepository.save(bill);
	}

}
