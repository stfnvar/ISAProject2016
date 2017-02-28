package com.restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.restaurant.model.Guest;

public interface GuestRepository extends JpaRepository<Guest, Long> {

	Guest findById(long id);
	
	@Modifying
	@Query("update Guest g set g.active=1 where g.id=?1")
	public void updateActive(long id);

}

