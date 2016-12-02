package com.restaurant.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class Reservation {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(nullable = false)
	private Table table;
	
	@Column(nullable = false)
	private Restaurant restaurant;
	
	@Column(nullable = false)
	private Date reservationDate;
	
}
