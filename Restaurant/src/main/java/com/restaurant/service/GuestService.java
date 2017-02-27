package com.restaurant.service;
import com.restaurant.model.Person;

import java.util.List;

import com.restaurant.model.Guest;

public interface GuestService {
	Guest save(Guest guest);
	
	Guest findOneById(Long id);
	
	void activateAccount(long id);
	void updateOneGuest(long id,String name, String surname, String email, String pass);
	void removeOneFriendship(long id1, long id2);
	
	List<Enemies> getNotFriends(long id);
	List<Friendship> getFriends(long id);
	
	List<Enemies> findGuestsByName(String name, long curr);
	List<Enemies> findGuestsBySurname(String surname, long curr);
	List<Enemies> findGuestsByNameAndSurname(String name, String surname, long curr);
	
	List<Friends> findFriendsByName(String name, long curr);
	List<Friends> findFriendsBySurname(String surname, long curr);
	List<Friends> findFriendsByNameAndSurname(String name, String surname, long curr);
	
	public void becomeFriends(long id1, long id2);
}
