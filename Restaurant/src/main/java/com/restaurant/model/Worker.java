package com.restaurant.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Worker implements Serializable{
	
	@Id //signifies the primary key
    @Column(name="ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long Id;
	
	@Column(nullable = false)
	@Enumerated(EnumType.ORDINAL)
	private WorkerType type;
	
	@Column(nullable = false)
	private int shift;
	
	@Column(nullable = false)
	private int workingHours;
	
	@ManyToOne(optional = false)
	private RestaurantManager restaurantManager;

}
