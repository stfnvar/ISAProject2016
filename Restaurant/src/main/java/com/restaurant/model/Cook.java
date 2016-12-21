package com.restaurant.model;

import javax.persistence.Column;
import javax.persistence.Entity;
@Entity
public class Cook extends Worker{
	
	@Column(nullable = false)
	private String uloga;
	
	public Cook(){}

}
