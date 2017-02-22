package com.restaurant.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class Person implements Serializable{
	
	@Id
	@GeneratedValue
	private Long id;
	
	 @ManyToMany
	  @JoinTable(
	      name="FriendshipTable",
	      joinColumns=@JoinColumn(name="G1_ID", referencedColumnName="ID"),
	      inverseJoinColumns=@JoinColumn(name="G2_ID", referencedColumnName="ID"))
	  private List<Person> friends;

	@Column(nullable = false)
	private String username;

	@Column(nullable = false)
	private String password;

	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private String surname;
	
	@Column(nullable = false)
	private String email;
	
	public Person(){}

	public Long getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getName() {
		return name;
	}

	public String getSurname() {
		return surname;
	}

	public String getEmail() {
		return email;
	}
	
	public void setId(Long id){
		this.id = id;
	}
}
