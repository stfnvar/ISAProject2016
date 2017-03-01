package com.restaurant.service;

import java.util.Set;

import com.restaurant.model.Invite;

public interface InviteService {

	
	
	void inviteOneFriend(long from, long to, String start, String end, long restid);
	
	Set<Invite> getMyInvites(long id);
	
	 void inviteMeWhenReserve(long from, long to, String start, String end, long restid);
	 
	 void acceptOneInvitation(long id);
	 void cancelOneInvitation(long id);
	 
	 long getIdInvitation(long from, long to, String start, String end, long restid);
}
