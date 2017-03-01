package com.restaurant.model;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
@Entity
public class Grocery {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(nullable = false)
	private String name;
	
	public String getName() {
		return name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	@OneToMany(targetEntity=WantedItems.class, cascade=CascadeType.ALL, mappedBy="grocery", orphanRemoval=true)
	private Set<WantedItems> wantedItems;
	
	
	public Grocery() {
		super();
	}

	public Grocery(String name, int quantity, int price) {
		super();
		this.name = name;
	}
}
