package com.restaurant.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restaurant.model.Guest;
import com.restaurant.repository.GuestRepository;

@Service
public class GuestServiceImpl implements GuestService{
	
	@Autowired
	GuestRepository guestRepository;
	
	@Override
	public Guest save(Guest guest) {
		return guestRepository.save(guest);
	}
	
}
