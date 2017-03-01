package com.restaurant.service;

import java.util.List;

import com.restaurant.model.Offer;

public interface OfferService {
	
	List<Offer> getAllRelevantOffersForManager(Long id);
	
	List<Offer> getAllRelevantOffersForRestaurant(Long id);
	
	List<Offer> getAllOffersForAnnouncement(Long id);
	
	List<Offer> getAllOffersForOfferer(Long id);
	
	Offer save(Offer of);
}
