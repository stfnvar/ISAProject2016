package com.restaurant.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity
public class Rating {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(nullable = false)
	private double rating;
	
	@Column(nullable = false)
	private User user;

	public Rating() {
		super();
	}

	public Rating(double rating, User user) {
		super();
		this.rating = rating;
		this.user = user;
	}
	
}
