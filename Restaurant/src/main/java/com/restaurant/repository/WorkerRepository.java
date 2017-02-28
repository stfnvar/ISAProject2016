package com.restaurant.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.restaurant.model.Worker;

public interface WorkerRepository extends JpaRepository<Worker, Long>  {

	Worker findByUsernameAndPassword(String username, String password);
	
	Worker findById(long id);
	
	@Query("select ws from Worker as ws where restaurant_id = ?1")
	List<Worker> getWorkersRestaurant(Long id);
}
