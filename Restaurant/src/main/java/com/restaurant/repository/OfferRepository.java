package com.restaurant.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.restaurant.model.Offer;

public interface OfferRepository extends JpaRepository<Offer, Long> {
	@Query("select ofs from Offer as ofs where ofs.announcement.restaurantManager.id=?1")
	List<Offer> getAllRelevantOffersForManager(Long id);
	
	@Query("select ofs from Offer as ofs where ofs.announcement.restaurantManager.restaurant.id=?1")
	List<Offer> getAllRelevantOffersForRestaurant(Long id);
	
	@Query("select ofs from Offer as ofs where ofs.announcement.id=?1")
	List<Offer> getAllOffersForAnnouncement(Long id);
	
	@Query("select ofs from Offer as ofs where ofs.offerer.id=?1")
	List<Offer> getOffersForOfferer(Long id);
}
