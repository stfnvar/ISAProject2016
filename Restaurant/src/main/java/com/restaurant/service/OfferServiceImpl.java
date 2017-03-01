package com.restaurant.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restaurant.model.Offer;
import com.restaurant.repository.OfferRepository;
import com.restaurant.repository.OffererRepository;
@Service
public class OfferServiceImpl implements OfferService{
	
	@Autowired
	private OfferRepository offerRepository;
	
	@Override
	public List<Offer> getAllRelevantOffersForManager(Long id) {
		return offerRepository.getAllRelevantOffersForManager(id);
	}

	@Override
	public List<Offer> getAllRelevantOffersForRestaurant(Long id) {
		return offerRepository.getAllRelevantOffersForRestaurant(id);
	}

	@Override
	public List<Offer> getAllOffersForAnnouncement(Long id) {
		return offerRepository.getAllOffersForAnnouncement(id);
	}

	@Override
	public List<Offer> getAllOffersForOfferer(Long id) {
		return offerRepository.getOffersForOfferer(id);
	}

	@Override
	public Offer save(Offer of) {
		// TODO Auto-generated method stub
		return offerRepository.save(of);
	}
	
}
