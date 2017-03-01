package com.restaurant.repository;

import java.util.Date;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.restaurant.model.WorkingSchedule;

public interface WorkingScheduleRepository extends JpaRepository<WorkingSchedule, Long> {

	@Query("select dutified from WorkingSchedule as dutified where date(start) = date(?1)")
	Set<WorkingSchedule> getOnDutyDay(Date startDay);
	
	Set<WorkingSchedule> findByStartAfterAndEndBefore(Date start, Date end);

}
