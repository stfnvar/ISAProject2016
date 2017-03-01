package com.restaurant.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.JoinColumn;

@Entity
public class Guest extends Person{
/*
	@ManyToMany
	 @JoinTable(name="FriendshipTable", 
			 	joinColumns=@JoinColumn(name="G1_ID", referencedColumnName="ID"),
  		  inverseJoinColumns=@JoinColumn(name="G2_ID", referencedColumnName="ID")
    )   private Set<Person> friends;
	*/	
		  
		@Column(nullable = false)
		private int active;
		
		@OneToMany(mappedBy = "guest", targetEntity = Reservation.class)
		private Set<Reservation> reservations;
		
		public Guest(){
			super();
		}
	

		public int getActive() {
			return active;
		}

		public void setActive(int active) {
			this.active = active;
		}

		
		
}
