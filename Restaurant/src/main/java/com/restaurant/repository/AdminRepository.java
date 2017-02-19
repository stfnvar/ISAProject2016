package com.restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.restaurant.model.Administrator;
import com.restaurant.model.DrinkCard;

public interface AdminRepository  extends JpaRepository<Administrator, Long>{

	Administrator findById(long id);
	
}
