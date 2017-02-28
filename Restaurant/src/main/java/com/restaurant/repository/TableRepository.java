package com.restaurant.repository;

import java.util.List;
import java.util.Set;

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
	
	@Query(value="select distinct  tables_id as id from (SELECT reservation_starts, reservation_ends, tables_id FROM reservation where tables_id in (select id from table_table where restaurant_id=?1))as v where (?2 between v.reservation_starts and v.reservation_ends ) or(?2 not between v.reservation_starts and v.reservation_ends  and ?3 between  v.reservation_starts and v.reservation_ends ) or(?2 <v.reservation_starts and ?3>v.reservation_ends)", nativeQuery=true)
	List<Integer> getWrongTables(long id, String start, String finale);
}
