package com.restaurant.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.restaurant.model.Guest;
import com.restaurant.service.Enemies;
import com.restaurant.service.Friends;
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
	
	@Query(value="SELECT id, name, surname FROM  (select g1_id,g2_id  from friendship_table where g1_id=?1)  as a LEFT JOIN person ON person.id=a.g2_id",nativeQuery = true)
	public List<Friendship> findFriendsById(long id);
	
	@Modifying
	@Query(value="delete from friendship_table where g1_id=?1 and g2_id=?2",nativeQuery=true)
	public void removeFriendship(long id1, long id2);

	//pronalazenje liste gostiju koju nisu prijatelji ulogovanom gostu
	@Query(value="select id,name,surname from(select fuck from((select id as fuck from guest where id not in (select g2_id from friendship_table where g1_id=?1 UNION select g2_id from friendship_table where g2_id=?1))as a))as v left join person on person.id=v.fuck", nativeQuery=true)
	public List<Enemies> findNotFriends(long loggedin);
	
	//OR search po imenu samo
	@Query(value="select id,name,surname from(select fuck from((select id as fuck from guest where id not in (select g2_id from friendship_table where g1_id=?2 UNION select g2_id from friendship_table where g2_id=?2))as a))as v left join person on person.id=v.fuck where name=?1", nativeQuery=true)
	public List<Enemies> findOrByName(String name, long current);
	
	//OR search po prezimenu samo
	@Query(value="select id,name,surname from(select fuck from((select id as fuck from guest where id not in (select g2_id from friendship_table where g1_id=?2 UNION select g2_id from friendship_table where g2_id=?2))as a))as v left join person on person.id=v.fuck where surname=?1", nativeQuery=true)
	public List<Enemies> findOrBySurname(String surname, long current);
	
	//AND search po imenu i prezimenu
	@Query(value="select id,name,surname from(select fuck from((select id as fuck from guest where id not in (select g2_id from friendship_table where g1_id=?3 UNION select g2_id from friendship_table where g2_id=?3))as a))as v left join person on person.id=v.fuck where name=?1 and surname=?2", nativeQuery=true)
	public List<Enemies> findAnd(String name, String surname, long curret);
	
	//pravljenje prijateljstva izmedju dva gosta
	@Modifying
	@Query(value="insert into friendship_table (g1_id, g2_id) values (?1, ?2)",nativeQuery=true)
	public void addFriendship(long id1, long id2);
	
		//OR search prijatelja po imenu samo
		@Query(value="select id,name,surname from((SELECT * FROM friendship_table where g1_id=?2) as a )left join person on person.id=a.g2_id where name=?1", nativeQuery=true)
		public List<Friends> findFriendsByName(String name, long current);
		
		//OR search prijatelja po prezimenu samo
		@Query(value="select id,name,surname from((SELECT * FROM friendship_table where g1_id=?2) as a )left join person on person.id=a.g2_id where surname=?1", nativeQuery=true)
		public List<Friends> findFriendsOrBySurname(String surname, long current);
		
		//AND search prijatelja po imenu i prezimenu
		@Query(value="select id,name,surname from((SELECT * FROM friendship_table where g1_id=?3) as a )left join person on person.id=a.g2_id where name=?1 and surname=?2" , nativeQuery=true)
		public List<Friends> findFriendsAnd(String name, String surname, long curret);

}

