package com.restaurant.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
@Entity
public class Cook extends Worker{
	
	@Column(nullable = false)
	@Enumerated(EnumType.ORDINAL)
	private CookType typeC;
	
	
	
	public CookType getTypeC() {
		return typeC;
	}

	public void setTypeC(CookType typeC) {
		this.typeC = typeC;
	}

	@Column(nullable = false)
	private String uloga;
	
	public Cook(){}

	public String getUloga() {
		return uloga;
	}

}
