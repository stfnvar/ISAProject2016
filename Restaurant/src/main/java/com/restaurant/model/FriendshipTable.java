package com.restaurant.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class FriendshipTable {

	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name="ID_KORISNIKA")
	private String id_korisnika;
	
	@Column(name="IME_KORISNIKA")
	private String name;
	
	@ManyToOne(optional = false)
	private Guest user;
	
	
	public FriendshipTable(){}
	
	
}
