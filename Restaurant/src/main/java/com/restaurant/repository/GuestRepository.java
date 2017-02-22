package com.restaurant.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.restaurant.model.Guest;
import com.restaurant.service.Friendship;

public interface GuestRepository extends JpaRepository<Guest, Long> {

	Guest findById(long id);
	
	@Modifying
	@Query("update Guest g set g.active=1 where g.id=?1")
	public void updateActive(long id);
	
	@Modifying(clearAutomatically = true)
	@Query("update Guest g set g.name=:name,g.surname=:surname,g.email=:email,g.password=:pass where g.id=:id")
	public void updateGuest(@Param("id") long id, @Param("name") String name, @Param("surname")
	String surname, @Param("email") String email, @Param("pass") String pass);
	
	@Query(value="SELECT id, email, surname FROM  (select g1_id,g2_id  from friendship_table where g1_id=?1)  as a LEFT JOIN person ON person.id=a.g2_id",nativeQuery = true)
	public List<Friendship> findFriendsById(long id);

}

