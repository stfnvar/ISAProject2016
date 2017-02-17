package com.restaurant.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

@Entity
public class Guest extends Person{

		@OneToMany(fetch = FetchType.LAZY, targetEntity=FriendshipTable.class, mappedBy = "user")
		private Set<FriendshipTable> friends;
		
		public Guest(){
			super();
		}
		
}
