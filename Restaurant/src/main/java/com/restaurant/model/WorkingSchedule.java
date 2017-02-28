package com.restaurant.model;

import java.sql.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class WorkingSchedule {

	@Id
	@GeneratedValue
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Worker getWorker() {
		return worker;
	}

	public void setWorker(Worker worker) {
		this.worker = worker;
	}

	public RestaurantSegment getSegment() {
		return segment;
	}

	public void setSegment(RestaurantSegment segment) {
		this.segment = segment;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public int getShift() {
		return shift;
	}

	public void setShift(int shift) {
		this.shift = shift;
	}

	@ManyToOne(optional = true)
	private Worker worker;

	@ManyToOne(optional = true)
	private RestaurantSegment segment;

	@Column(nullable = true)
	private Date start;

	@Column(nullable = true)
	private Date end;

	@Column(nullable = true)
	private int shift;
	
	
	public WorkingSchedule() {
	}

}
