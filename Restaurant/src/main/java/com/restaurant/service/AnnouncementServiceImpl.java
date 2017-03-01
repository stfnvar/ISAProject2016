package com.restaurant.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import com.restaurant.model.Announcement;
import com.restaurant.repository.AnnouncementRepository;
@Service
public class AnnouncementServiceImpl implements AnnouncementService{
	
	@Autowired 
	AnnouncementRepository announcementRepository;

	@Override
	public Set<Announcement> getAnnouncementRestaurantManager(Long id) {
		return announcementRepository.getAnnouncementsForManager(id);
	}

	@Override
	public void removeOne(Long id) {
		announcementRepository.delete(id);
	}

	@Override
	public Announcement addOne(Announcement announcement) {
		return announcementRepository.save(announcement);
	}

	@Override
	public List<Announcement> getAllAnnouncements() {
		return announcementRepository.findAll();
	}

	@Override
	public Announcement findOne(Long id) {
		return announcementRepository.findOne(id);
	}

}
