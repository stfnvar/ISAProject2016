package com.restaurant.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.restaurant.model.Invite;

public interface InviteRepository extends JpaRepository<Invite, Long>{

	
	@Modifying
	@Query(value="INSERT INTO `invite` ( `accepted`, `been`, `end`, `start`, `from_id`, `to_id`,`rest_id`) VALUES ( '0', '0', ?4, ?3, ?1, ?2, ?5)", nativeQuery=true)
	void inviteFriend(long from, long to, String start, String end, long restid);
	
	@Modifying
	@Query(value="INSERT INTO `invite` ( `accepted`, `been`, `end`, `start`, `from_id`, `to_id`,`rest_id`) VALUES ( '1', '0', ?4, ?3, ?1, ?2, ?5)", nativeQuery=true)
	void inviteMe(long from, long to, String start, String end, long restid);
	
	@Query("select inv from Invite as inv where to_id=?1 and from_id<>?1")
	 Set<Invite> getMyInvitations(long id);
	
	@Modifying
	@Query(value="update invite  set accepted=1 where id=?1", nativeQuery=true)
	void acceptInvitation(long id);
	
	
	@Query(value="select id from invite where from_id=?1 and to_id=?2 and start=?3 and end=?4 and rest_id=?5", nativeQuery=true)
	Long idFromInvitation(long from, long to, String start, String end, long restid);

	
	@Modifying
	@Query(value="update invite  set accepted=0 where id=?1", nativeQuery=true)
	void cancelInvitation( long id);


}
