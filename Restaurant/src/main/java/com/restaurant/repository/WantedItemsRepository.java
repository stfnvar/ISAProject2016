package com.restaurant.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.restaurant.model.WantedItems;

public interface WantedItemsRepository extends JpaRepository<WantedItems, Long>{
	@Query("select wi from WantedItems as wi where wi.announcement.id = ?1")
	List<WantedItems> getWantedItemsForAnnouncement(Long id);
}
