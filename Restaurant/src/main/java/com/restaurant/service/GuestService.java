package com.restaurant.service;
import com.restaurant.model.Person;

import java.util.List;

import com.restaurant.model.Guest;

public interface GuestService {
	Guest save(Guest guest);
	
	Guest findOneById(Long id);
	
	void activateAccount(long id);
	void updateOneGuest(long id,String name, String surname, String email, String pass);
	
	List<Friendship> getFriends(long id);
}
