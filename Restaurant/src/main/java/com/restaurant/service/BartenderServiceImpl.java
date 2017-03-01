package com.restaurant.service;

import java.util.Date;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restaurant.model.Bartender;
import com.restaurant.model.WorkingSchedule;
import com.restaurant.repository.BartenderRepository;

@Service
public class BartenderServiceImpl implements BartenderService {
	
	@Autowired
	BartenderRepository bartenderRepository;

	@Override
	public Bartender findOneById(Long id) {
		// TODO Auto-generated method stub
		return bartenderRepository.findOne(id);
	}

	@Override
	public void changePW(long id, String password) {
		// TODO Auto-generated method stub
		bartenderRepository.ftUpdatePW(id, password);
	}

	@Override
	public void changeFT(long id) {
		// TODO Auto-generated method stub
		bartenderRepository.changeFT(id);
	}

	@Override
	public void updateOneBartender(long id, String name, String surname, String email, String pass) {
		// TODO Auto-generated method stub
		bartenderRepository.updateBartender(id, name, surname, email, pass);
	}

	@Override
	public Set<WorkingSchedule> getOnDutyDay(Date startDay, Long id) {
		// TODO Auto-generated method stub
		return bartenderRepository.getOnDutyDay(startDay, id);
	}

}
