package com.restaurant.model;

import javax.persistence.Column;
import javax.persistence.Entity;
@Entity
public class Administrator extends Person{
	
	
	@Column(nullable = false)
	private boolean supreme;
	
	public Administrator(){}

	public boolean isSupreme() {
		return supreme;
	}

	public void setSupreme(boolean supreme) {
		this.supreme = supreme;
	}
	
}
