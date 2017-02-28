package com.restaurant.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class OrderedMeal {
	
	@Column(nullable = false)
	private Integer acceptedMeal;
	
	
	
	public Integer getAcceptedMeal() {
		return acceptedMeal;
	}

	public void setAcceptedMeal(Integer acceptedMeal) {
		this.acceptedMeal = acceptedMeal;
	}

	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false)
	private Integer ready;
	
	@ManyToOne(optional = true)
	private Order order;
	
	@ManyToOne(optional = true)
	private Meal meal;
	
	@Column
	private int quantity;

	public OrderedMeal() {
		super();
	}

	public Integer getReady() {
		return ready;
	}

	public Order getOrder() {
		return order;
	}

	public Meal getMeal() {
		return meal;
	}

	public int getQuantity() {
		return quantity;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setReady(Integer ready) {
		this.ready = ready;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public void setMeal(Meal meal) {
		this.meal = meal;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
	
	
	
}
