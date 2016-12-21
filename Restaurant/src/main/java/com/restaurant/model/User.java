package com.restaurant.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
@Entity
public class User extends Person{
	
	
		
		@OneToMany(fetch = FetchType.LAZY, targetEntity=FriendshipTable.class, mappedBy = "user")
		private Set<FriendshipTable> friends;
		
		
		
		protected User(){
			super();
		}
	
		
}
