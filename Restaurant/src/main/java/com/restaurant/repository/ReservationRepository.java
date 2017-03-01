package com.restaurant.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.restaurant.model.Invite;
import com.restaurant.model.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long>{
	
	@Modifying
	@Query(value="INSERT INTO reservation ( `reservation_ends`, `reservation_starts`, `guest_id`, `tables_id`) VALUES ( ?2, ?1, ?4, ?3)", nativeQuery=true)
	void reserveTables(String start, String end, int table, long who);
	
	@Query(value="select tables_id from reservation where guest_id=?3 and reservation_starts=?1 and reservation_ends=?2", nativeQuery=true)
	ArrayList<Integer> stoloviZaTerminIRezervanta(String starts, String ends, long rezervant);

}
