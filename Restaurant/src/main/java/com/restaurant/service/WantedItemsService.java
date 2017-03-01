package com.restaurant.service;

import java.util.List;

import com.restaurant.model.WantedItems;

public interface WantedItemsService {
	WantedItems findOne(Long id);
	List<WantedItems> getAllForAnnouncement(Long id);
	WantedItems save(WantedItems wi);
	void remove(Long id);
}
