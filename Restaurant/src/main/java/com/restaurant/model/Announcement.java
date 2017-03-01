package com.restaurant.model;

import java.util.Date;
import java.util.List;
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
public class Announcement {

	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false)
	boolean accepted;

	public boolean isAccepted() {
		return accepted;
	}

	public void setAccepted(boolean accepted) {
		this.accepted = accepted;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = WantedItems.class, orphanRemoval = true, mappedBy = "announcement")
	private Set<WantedItems> wantedItems;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = WantedDrink.class, orphanRemoval = true, mappedBy = "announcement")
	private Set<WantedDrink> wantedDrink;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = Offer.class, orphanRemoval = true, mappedBy = "announcement")
	private Set<Offer> offers;

	public RestaurantManager getRestaurantManager() {
		return restaurantManager;
	}

	public void setRestaurantManager(RestaurantManager restaurantManager) {
		this.restaurantManager = restaurantManager;
	}

	@ManyToOne(optional = false)
	private RestaurantManager restaurantManager;

	@Column(nullable = false)
	private Date start;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	@Column(nullable = false)
	private Date end;

}
