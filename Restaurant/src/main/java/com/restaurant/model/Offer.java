package com.restaurant.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Offer {

	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false)
	private double price;

	@Column(nullable = false)
	private int warranty;

	@Column(nullable = false)
	private boolean accepted;

	public boolean isAccepted() {
		return accepted;
	}

	public void setAccepted(boolean accepted) {
		this.accepted = accepted;
	}

	public Announcement getAnnouncement() {
		return announcement;
	}

	public void setAnnouncement(Announcement announcement) {
		this.announcement = announcement;
	}

	@ManyToOne(optional = false)
	private Offerer offerer;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getWarranty() {
		return warranty;
	}

	public void setWarranty(int warranty) {
		this.warranty = warranty;
	}

	public Offerer getOfferer() {
		return offerer;
	}

	@ManyToOne(optional = false)
	private Announcement announcement;

	public Offer() {
		super();
	}

}
