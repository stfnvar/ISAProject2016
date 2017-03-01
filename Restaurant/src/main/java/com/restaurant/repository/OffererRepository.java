package com.restaurant.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.restaurant.model.Offerer;
import com.restaurant.model.RestaurantManager;

public interface OffererRepository extends JpaRepository<Offerer, Long>{
	
	@Query("update Offerer p set p.firstTime=?1 where p.id=?2")
	@Modifying
	void setFirstTime(boolean b, Long id);
	
}
