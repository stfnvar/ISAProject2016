package com.restaurant.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Worker extends Person {

	@Column(nullable = false)
	@Enumerated(EnumType.ORDINAL)
	private WorkerType type;

	@Column(nullable = true)
	private int shift;

	@Column(nullable = true)
	private int workingHours;

	@Column(nullable = false)
	private Date birthDate;

	@Column(nullable = false)
	private String shoeSize;

	@Column(nullable = false)
	private String wearSize;
	
	@OneToMany(cascade=CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval=true,targetEntity=WorkingSchedule.class, mappedBy = "worker")
	private Set<WorkingSchedule> workingSchedules;

	public WorkerType getType() {
		return type;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getShoeSize() {
		return shoeSize;
	}

	public void setShoeSize(String shoeSize) {
		this.shoeSize = shoeSize;
	}

	public String getWearSize() {
		return wearSize;
	}

	public void setWearSize(String wearSize) {
		this.wearSize = wearSize;
	}

	public void setType(WorkerType type) {
		this.type = type;
	}

	public int getShift() {
		return shift;
	}

	public void setShift(int shift) {
		this.shift = shift;
	}

	public int getWorkingHours() {
		return workingHours;
	}

	public void setWorkingHours(int workingHours) {
		this.workingHours = workingHours;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	@ManyToOne(optional = false)
	private Restaurant restaurant;
	
}
