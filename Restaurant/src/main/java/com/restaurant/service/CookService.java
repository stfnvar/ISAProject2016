package com.restaurant.service;

import com.restaurant.model.Cook;

public interface CookService {

	Cook findOneById(Long id);
	
	void changePW(long id, String password);
	
	void changeFT(long id);
	
	void updateOneCook(long id,String name, String surname, String email, String pass);

	
}
