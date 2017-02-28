package com.restaurant.service;

import java.util.Date;
import java.util.Set;

import com.restaurant.model.Waiter;
import com.restaurant.model.WorkingSchedule;

public interface WaiterService {

	Set<WorkingSchedule> getOnDutyDay(Date startDay, Long id);
	
	Waiter findOneById(Long id);
	
	void changePW(long id, String password);
	
	void changeFT(long id);
	
	void updateOneWaiter(long id,String name, String surname, String email, String pass);

}
