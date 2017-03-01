package com.restaurant.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restaurant.repository.ReservationRepository;

@Service
public class ReservationServiceImpl implements ReservationService{

	@Autowired
	ReservationRepository reservationRepo;
	
	@Override
	public void reserveOneTable(String start, String end, int table, long who) {
		reservationRepo.reserveTables(start, end, table, who);
		
		
	}

	@Override
	public ArrayList<Integer> vratiStoloveDaNarucim(String starts, String ends, long rezervant) {
		
		return reservationRepo.stoloviZaTerminIRezervanta(starts, ends, rezervant);
	}

	
	
}
