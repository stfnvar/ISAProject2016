package com.restaurant.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

@Entity
public class Guest extends Person{

		@OneToMany(fetch = FetchType.LAZY, targetEntity=FriendshipTable.class, mappedBy = "user")
		private Set<FriendshipTable> friends;
		
		@Column(nullable = false)
		private int active;
		
		public Guest(){
			super();
		}
		
		public Set<FriendshipTable> getFriends() {
			return friends;
		}

		public void setFriends(Set<FriendshipTable> friends) {
			this.friends = friends;
		}

		public int getActive() {
			return active;
		}

		public void setActive(int active) {
			this.active = active;
		}

		
		
}
