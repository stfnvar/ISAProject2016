package com.restaurant.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restaurant.model.WantedItems;
import com.restaurant.repository.WantedItemsRepository;

@Service
public class WantedItemsServiceImpl  implements WantedItemsService{
	
	@Autowired
	WantedItemsRepository wir;
	
	@Override
	public WantedItems findOne(Long id) {
		return wir.findOne(id);
	}

	@Override
	public List<WantedItems> getAllForAnnouncement(Long id) {
		// TODO Auto-generated method stub
		return wir.getWantedItemsForAnnouncement(id);
	}

	@Override
	public WantedItems save(WantedItems wi) {
		return wir.save(wi);
	}

	@Override
	public void remove(Long id) {
		wir.delete(id);
	}

}
