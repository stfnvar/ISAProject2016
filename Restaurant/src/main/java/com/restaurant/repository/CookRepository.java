package com.restaurant.repository;

import java.util.Date;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.restaurant.model.Cook;
import com.restaurant.model.Waiter;
import com.restaurant.model.WorkingSchedule;

public interface CookRepository extends JpaRepository<Cook, Long>{
	
	@Query("select dutified from WorkingSchedule as dutified where date(start) = date(?1) and dutified.worker.id = ?2")
	Set<WorkingSchedule> getOnDutyDay(Date startDay, Long id);
	
	@Query("select cooks from Cook as cooks where restaurant_id=?1")
	Set<Cook> getRestaurantStaffCooks(Long id);
	
	public Cook findOne(Long id);
	
	@Modifying
	@Query("update Person p set p.password=?2 where p.id=?1")
	public void ftUpdatePW(long id, String pw);
	
	@Modifying
	@Query("update Worker w set w.firstTimeChangePW=1 where w.id=?1")
	public void changeFT(long id);

	@Modifying(clearAutomatically = true)
	@Query("update Cook c set c.name=:name,c.surname=:surname,c.email=:email,c.password=:pass where c.id=:id")
	public void updateCook(@Param("id") long id, @Param("name") String name, @Param("surname")
	String surname, @Param("email") String email, @Param("pass") String pass);
}
