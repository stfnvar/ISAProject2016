package com.restaurant.repository;

import java.util.Set;

import javax.transaction.Transactional;

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

}
