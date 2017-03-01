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
public class Invite {
	@Id
	@GeneratedValue
	private Long id;
	
	@ManyToOne(optional = false)
	private Guest from;
	
	@ManyToOne(optional = false)
	private Guest to;
	
	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date start;
	
	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date end;
	
	@Column(nullable = false)
	private int accepted;
	
	@Column(nullable = false)
	private int been;
	
	@ManyToOne(optional = false)
	private Restaurant rest;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Guest getFrom() {
		return from;
	}

	public void setFrom(Guest from) {
		this.from = from;
	}

	public Guest getTo() {
		return to;
	}

	public void setTo(Guest to) {
		this.to = to;
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

	public int getAccepted() {
		return accepted;
	}

	public void setAccepted(int accepted) {
		this.accepted = accepted;
	}

	public int getBeen() {
		return been;
	}

	public void setBeen(int been) {
		this.been = been;
	}

	public Restaurant getRest() {
		return rest;
	}

	public void setRest(Restaurant rest) {
		this.rest = rest;
	}

	public Invite(){}
	
	
	
}
