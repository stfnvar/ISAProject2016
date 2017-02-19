package com.restaurant.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restaurant.model.Worker;
import com.restaurant.repository.WorkerRepository;
@Service
public class WorkerServiceImpl implements WorkerService{
	
	@Autowired
	WorkerRepository workerRepository;

	@Override
	public Worker findOneByUsernameAndPassword(String username, String password) {
		
		return workerRepository.findByUsernameAndPassword(username, password);
	}

	@Override
	public Worker findOneById(long id) {
		
		return workerRepository.findById(id);
	}

}
