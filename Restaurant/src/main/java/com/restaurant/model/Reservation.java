package com.restaurant.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
@Entity
public class Reservation {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date reservationStarts;
	
	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date reservationEnds;
	
	@ManyToOne(optional = true)
   	private Table tables;
	@ManyToOne(optional = false)
	private Guest guest;
	
}
