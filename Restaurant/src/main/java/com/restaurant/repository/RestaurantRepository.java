package com.restaurant.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.restaurant.model.Restaurant;
import com.restaurant.service.Restaurants;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
	
	Restaurant findOne(Long id);
	
	//@Query(value="select id, name from restaurant", nativeQuery=true)
	List<Restaurant> findAll();
	
	//@Query(value="select * from Restaurant where id in (select to_id from invite where to_id=?1)",nativeQuery=true)
	@Query(value="select distinct r.id, r.name, r.description from restaurant r join invite i on r.id=i.rest_id and i.accepted=1 and to_id=?1", nativeQuery=true)
	List<Restaurant> getForHistory(long id);
	
}
