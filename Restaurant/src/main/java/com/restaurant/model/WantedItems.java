package com.restaurant.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class WantedItems {

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
	
	@Column(nullable = false)
	Integer quanitiy;
	
	public Integer getQuanitiy() {
		return quanitiy;
	}

	public void setQuanitiy(Integer quanitiy) {
		this.quanitiy = quanitiy;
	}

	@ManyToOne(optional = false)
	private Announcement announcement;
	
	
	public Grocery getGrocery() {
		return grocery;
	}

	public void setGrocery(Grocery grocery) {
		this.grocery = grocery;
	}

	public Announcement getAnnouncement() {
		return announcement;
	}

	public void setAnnouncement(Announcement announcement) {
		this.announcement = announcement;
	}

	@ManyToOne(optional = false)
	private Grocery grocery;
	
}
