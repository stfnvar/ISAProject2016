package com.restaurant.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class Worker extends Person{
	
	@Column(nullable = false)
	@Enumerated(EnumType.ORDINAL)
	private WorkerType type;
	
	@Column(nullable = false)
	private int shift;
	
	@Column(nullable = false)
	private int workingHours;
	
	@ManyToOne(optional = false)
	private Restaurant restaurant;

}
