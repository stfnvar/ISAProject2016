package com.restaurant.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restaurant.model.Guest;
import com.restaurant.model.Person;
import com.restaurant.repository.GuestRepository;
import com.restaurant.service.Enemies;

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

	@Override
	public void removeOneFriendship(long id1, long id2) {
		 guestRepository.removeFriendship(id1, id2);
		
	}

	@Override
	public List<Enemies> getNotFriends(long id) {
		 
		return guestRepository.findNotFriends(id);
	}

	@Override
	public List<Enemies> findGuestsByName(String name, long curr) {
		
		return guestRepository.findOrByName(name,  curr);
	}

	@Override
	public List<Enemies> findGuestsBySurname(String surname, long curr) {
		
		return guestRepository.findOrBySurname(surname, curr);
	}

	@Override
	public List<Enemies> findGuestsByNameAndSurname(String name, String surname, long curr) {
		
		return guestRepository.findAnd(name, surname, curr);
	}

	@Override
	public void becomeFriends(long id1, long id2) {
		guestRepository.addFriendship(id1, id2);
		
	}

	@Override
	public List<Friends> findFriendsByName(String name, long curr) {
		
		return guestRepository.findFriendsByName(name, curr);
	}

	@Override
	public List<Friends> findFriendsBySurname(String surname, long curr) {
		
		return guestRepository.findFriendsOrBySurname(surname, curr);
	}

	@Override
	public List<Friends> findFriendsByNameAndSurname(String name, String surname, long curr) {
		
		return guestRepository.findFriendsAnd(name, surname, curr);
	}
	
	
}
