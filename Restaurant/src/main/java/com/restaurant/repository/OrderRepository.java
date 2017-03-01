package com.restaurant.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.restaurant.model.Order;
import com.restaurant.model.Table;

public interface OrderRepository extends JpaRepository<Order, Long>{
	
	@Query("select o from Order as o where o.table.restaurant.id=?1")
	public ArrayList<Order> findAll(Long id);
	

	
}
