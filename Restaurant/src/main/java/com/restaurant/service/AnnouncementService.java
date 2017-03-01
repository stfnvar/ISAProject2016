package com.restaurant.service;

import java.util.List;
import java.util.Set;

import com.restaurant.model.Announcement;

public interface AnnouncementService {
	Set<Announcement> getAnnouncementRestaurantManager(Long id);
	void removeOne(Long id);
	Announcement addOne(Announcement announcement);
	List<Announcement> getAllAnnouncements();
	Announcement findOne(Long id);
}
