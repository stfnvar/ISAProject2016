package com.restaurant.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restaurant.model.Invite;
import com.restaurant.repository.InviteRepository;

@Service
public class InviteServiceImpl implements InviteService{

	
	@Autowired
	InviteRepository inviteRepo;
	
	@Override
	public void inviteOneFriend(long from, long to, String start, String end, long restid) {
		inviteRepo.inviteFriend(from, to, start, end, restid);
		
	}

	@Override
	public Set<Invite> getMyInvites(long id) {
		
		return inviteRepo.getMyInvitations(id);
	}

	@Override
	public void inviteMeWhenReserve(long from, long to, String start, String end, long restid) {
		inviteRepo.inviteMe(from, to, start, end, restid);
		
	}

	@Override
	public void acceptOneInvitation(long id) {
		inviteRepo.acceptInvitation(id);
		
	}

	@Override
	public void cancelOneInvitation(long id) {
		inviteRepo.cancelInvitation(id);
		
	}

	@Override
	public long getIdInvitation(long from, long to, String start, String end, long restid) {
		
		return inviteRepo.idFromInvitation(from, to, start, end, restid);
	}

}
