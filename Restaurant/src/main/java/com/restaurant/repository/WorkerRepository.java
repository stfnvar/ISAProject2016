package com.restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.restaurant.model.Worker;

public interface WorkerRepository extends JpaRepository<Worker, Long>  {

	Worker findByUsernameAndPassword(String username, String password);
	
	Worker findById(long id);
	
}
