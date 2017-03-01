package com.restaurant.service;

import java.util.Date;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restaurant.model.Waiter;
import com.restaurant.model.WorkingSchedule;
import com.restaurant.repository.WaiterRepository;

@Service
public class WaiterServiceImpl implements WaiterService {

	@Autowired
	private WaiterRepository waiterRepository;
	
	
	@Override
	public Waiter findOneById(Long id) {
		// TODO Auto-generated method stub
		return waiterRepository.findOne(id);
	}


	@Override
	public void changePW(long id, String password) {
		// TODO Auto-generated method stub
		waiterRepository.ftUpdatePW(id, password);
	}


	@Override
	public void changeFT(long id) {
		// TODO Auto-generated method stub
		waiterRepository.changeFT(id);
	}


	@Override
	public void updateOneWaiter(long id, String name, String surname, String email, String pass) {
		// TODO Auto-generated method stub
		waiterRepository.updateGuest(id, name, surname, email, pass);
	}


	@Override
	public Set<WorkingSchedule> getOnDutyDay(Date startDay, Long id) {
		// TODO Auto-generated method stub
		return waiterRepository.getOnDutyDay(startDay, id);
	}

}
