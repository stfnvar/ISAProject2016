package com.restaurant.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ManyToOne;
import javax.persistence.Id;

@Entity
public class WantedDrink {
	
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
	
	
	public void setAnnouncement(Announcement announcement) {
		this.announcement = announcement;
	}
	
	@ManyToOne(optional = false)
	private Drink drink;
	
	
	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}
}


