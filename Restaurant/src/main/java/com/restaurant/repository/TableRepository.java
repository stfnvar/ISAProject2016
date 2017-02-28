package com.restaurant.repository;

import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.restaurant.model.Table;

public interface TableRepository extends JpaRepository<Table, Long>{

	@Query("select table from Table as table where id=?1")
	Table findById(Long id);
	
	@Query("select tables from Table as tables where id=?1 and restaurant_id=?2")
	Set<Table> findByIdAndRestaurant_Id(Long id, Long restaurant_id);
	
	@Query("select tables from Table as tables where id=?1 and restaurant_id=?2 and restaurant_segment_id=?1")
	Set<Table> findByIdAndRestaurant_IdAndRestaurantSegment_Id(Long id, Long rid, Long rsid);
	
	@Query("select tables from Table as tables where restaurant_id=?1")
	Set<Table> findByRestaurantId(Long id);

	@Query("select tables from Table as tables where restaurant_segment_id=?1")
	Set<Table> findByRestaurantSegmentId(Long id);
	
	@Query("update Table set restaurant_segment_id=?1 where id=?2")
	@Modifying
	void updateTable(Long id, Long id2);
}
