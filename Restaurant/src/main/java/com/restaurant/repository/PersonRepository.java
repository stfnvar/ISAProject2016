package com.restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.restaurant.model.Person;

public interface PersonRepository extends JpaRepository<Person, Long>{
	
	public Person findByUsername(String username);
	
	public Person findByEmail(String email);
	
	public Person findByEmailAndPassword(String email, String password);
	
	public Person findOne(Long id);
	
	@Modifying
	@Query("UPDATE Person p SET p.name=?1, p.surname=?2 WHERE p.id=?3")
	public void updateForTypeRestaurantManager(String name, String surname, long id);
	
	@Modifying
	@Query("UPDATE Person p SET p.password=?2 WHERE p.id=?1")
	public void changePasswordForTypeRestaurantManager(Long id, String password);

}
