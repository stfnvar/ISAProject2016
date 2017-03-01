package com.restaurant.service;

import java.util.Date;
import java.util.Set;

import com.restaurant.model.Cook;
import com.restaurant.model.WorkingSchedule;

public interface CookService {
	
	Set<WorkingSchedule> getOnDutyDay(Date startDay, Long id);

	Cook findOneById(Long id);
	
	void changePW(long id, String password);
	
	void changeFT(long id);
	
	void updateOneCook(long id,String name, String surname, String email, String pass);

	
}
