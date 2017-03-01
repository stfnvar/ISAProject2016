package com.restaurant.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.restaurant.model.Announcement;

public interface AnnouncementRepository extends JpaRepository<Announcement, Long>{
	@Query("select an from Announcement as an where restaurant_manager_id=?1")
	Set<Announcement> getAnnouncementsForManager(Long id);
}
