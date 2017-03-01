package com.restaurant.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restaurant.model.Rating;
import com.restaurant.repository.RatingRepository;
@Service
public class RatingServiceImpl implements RatingService{
	
	@Autowired
	RatingRepository ratingRepository;

	@Override
	public void save(Rating rating) {
		// TODO Auto-generated method stub
		ratingRepository.save(rating);
	}

}
