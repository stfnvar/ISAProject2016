package com.restaurant.service;

import java.util.Date;
import java.util.Set;

import com.restaurant.model.Bartender;
import com.restaurant.model.WorkingSchedule;

public interface BartenderService {

	Set<WorkingSchedule> getOnDutyDay(Date startDay, Long id);
	
	Bartender findOneById(Long id);
	
	void changePW(long id, String password);
	
	void changeFT(long id);
	
	void updateOneBartender(long id,String name, String surname, String email, String pass);


}
