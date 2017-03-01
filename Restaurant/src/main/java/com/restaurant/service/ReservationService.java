package com.restaurant.service;

import java.util.ArrayList;

public interface ReservationService {

	
	void reserveOneTable(String start, String end, int table, long who);
	
	ArrayList<Integer> vratiStoloveDaNarucim(String starts, String ends, long rezervant);
}
