package com.restaurant.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restaurant.model.Guest;
import com.restaurant.model.Person;
import com.restaurant.repository.GuestRepository;

@Service
public class GuestServiceImpl implements GuestService{
	
	@Autowired
	GuestRepository guestRepository;
	
	@Override
	public Guest save(Guest guest) {
		return guestRepository.save(guest);
	}

	@Override
	public Guest findOneById(Long id) {
		
		return guestRepository.findById(id);
	}

	@Override
	public void activateAccount(long id) {
		
		 guestRepository.updateActive(id);
		
	}

	@Override
	public void updateOneGuest(long id, String name, String surname, String email, String pass) {
		guestRepository.updateGuest(id, name, surname, email, pass);
		
	}

	@Override
	public List<Friendship> getFriends(long id) {
		
		return guestRepository.findFriendsById(id);
	}
	
	
}
