package com.restaurant.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restaurant.model.Administrator;
import com.restaurant.repository.AdminRepository;

@Service
public class AdminServiceImpl implements AdminService {

	
	@Autowired
	AdminRepository adminRepo;
	
	@Override
	public Administrator findOneById(long id) {
		
		return adminRepo.findById(id);
	}

}
