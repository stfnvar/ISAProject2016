package com.restaurant.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
@Entity
public class Reservation {
	
	@Id
	@GeneratedValue
	private Long id;
	
	
	
	@ManyToOne(optional = false)
	private Restaurant restaurant;
	
	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date reservationDate;
	
	  @OneToOne(optional=false,cascade=CascadeType.ALL, 
   	       mappedBy="reservation",targetEntity=Table.class)
   	       private Table table; 
	
}
