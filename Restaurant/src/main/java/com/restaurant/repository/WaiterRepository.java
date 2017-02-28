package com.restaurant.repository;

import com.restaurant.model.Waiter;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

public interface WaiterRepository extends JpaRepository<Waiter, Long>  {

	
	
}
