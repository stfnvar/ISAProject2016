package com.restaurant.repository;

import com.restaurant.model.Waiter;
import com.restaurant.model.WorkingSchedule;

import java.util.Date;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface WaiterRepository extends JpaRepository<Waiter, Long>  {

	@Query("select dutified from WorkingSchedule as dutified where date(start) = date(?1) and dutified.worker.id = ?2")
	Set<WorkingSchedule> getOnDutyDay(Date startDay, Long id);

	@Query("select waiters from Waiter as waiters where restaurant_id=?1")
	Set<Waiter> getRestaurantStaffWaiters(Long id);
	
	@Query("select avg(r.rating) from Waiter w join w.ratings r where lower(w.name) like ?1 or w.surname like ?2 and w.restaurant.id = ?3")
	Double waiterRating(String name, String surname, Long id);
	
	public Waiter findOne(Long id);
	
	@Modifying
	@Query("update Person p set p.password=?2 where p.id=?1")
	public void ftUpdatePW(long id, String pw);
	
	@Modifying
	@Query("update Worker w set w.firstTimeChangePW=1 where w.id=?1")
	public void changeFT(long id);
	
	
	@Modifying(clearAutomatically = true)
	@Query("update Waiter w set w.name=:name,w.surname=:surname,w.email=:email,w.password=:pass where w.id=:id")
	public void updateGuest(@Param("id") long id, @Param("name") String name, @Param("surname")
	String surname, @Param("email") String email, @Param("pass") String pass);
}
