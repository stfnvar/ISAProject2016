package com.restaurant.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restaurant.model.Cook;
import com.restaurant.repository.CookRepository;

@Service
public class CookServiceImpl implements CookService{
	
	@Autowired
	CookRepository cookRepository;

	@Override
	public Cook findOneById(Long id) {
		// TODO Auto-generated method stub
		return cookRepository.findOne(id);
	}

	@Override
	public void changePW(long id, String password) {
		// TODO Auto-generated method stub
		cookRepository.ftUpdatePW(id, password);
	}

	@Override
	public void changeFT(long id) {
		// TODO Auto-generated method stub
		cookRepository.changeFT(id);
	}

	@Override
	public void updateOneCook(long id, String name, String surname, String email, String pass) {
		// TODO Auto-generated method stub
		cookRepository.updateCook(id, name, surname, email, pass);
	}

}
