package com.restaurant.service;

import com.restaurant.model.Worker;

public interface WorkerService {

	Worker findOneByUsernameAndPassword(String username, String password);
	Worker findOneById(long id);
}
