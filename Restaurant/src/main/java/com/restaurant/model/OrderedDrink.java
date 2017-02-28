package com.restaurant.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * @author User
 *
 */
@Entity
public class OrderedDrink {

	@Column(nullable = false)
	private Integer ready;
	
	@Id
	@GeneratedValue
	private Long id;
	
	@ManyToOne(optional = true)
	private Order order;
	
	@ManyToOne(optional = true)
	private Drink drink;
	
	@Column
	private int quantity;

	public OrderedDrink() {
		super();
	}

	public Long getId() {
		return id;
	}

	public Order getOrder() {
		return order;
	}

	public Drink getDrink() {
		return drink;
	}

	public int getQuantity() {
		return quantity;
	}

	public int getReady() {
		return ready;
	}

	public void setReady(Integer ready) {
		this.ready = ready;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public void setDrink(Drink drink) {
		this.drink = drink;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
	
}
