package com.restaurant.repository;

import java.util.ArrayList;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import com.restaurant.model.RestaurantSegment;

public interface RestaurantSegmentRepository extends JpaRepository<RestaurantSegment, Long> {
	
	@Query("select segment from RestaurantSegment as segment where restaurant_id = ?1")
	Set<RestaurantSegment> findByRestaurant_Id(Long id);
	
	@Query("update RestaurantSegment rs set rs.name=?1, rs.typeOf = ?2 where rs.id=?3")
	@Modifying
	void updateSegment(String name, String typeOf, Long id);
	
	
	@Query("select rs from RestaurantSegment as rs where rs.restaurant.id=?1")
	ArrayList<RestaurantSegment> findRestaurantSegmentsInRestaurant(Long id);
	
	@Query("select rs.segment from WorkingSchedule as rs where rs.worker.id=?1")
	ArrayList<RestaurantSegment> findRestaurantSegmentsByWorker(Long id);
}
