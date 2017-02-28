package com.restaurant.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

public class FriendshipTable {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@ManyToMany
    @JoinTable(name="has_friends", joinColumns={@JoinColumn(name="guest_id")},
    inverseJoinColumns={@JoinColumn(name="friend_id")})
    private Set<Guest> friends;
	
	 
	 @Column(nullable = false)
	 private String flag;
	 
	 FriendshipTable(){}
}
