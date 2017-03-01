package com.restaurant.controller;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restaurant.miscel.MessageWithObj;
import com.restaurant.miscel.SendMail;
import com.restaurant.model.Person;
import com.restaurant.service.InviteServiceImpl;
import com.restaurant.service.ReservationServiceImpl;
import com.restaurant.service.RestaurantServiceImpl;
import com.restaurant.service.TableServiceImpl;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {

	
	@Autowired
	RestaurantServiceImpl restaurantServiceImpl;
	
	@Autowired
	TableServiceImpl tableServiceImpl;
	
	@Autowired
	ReservationServiceImpl reservationServiceImpl;
	
	@Autowired
	InviteServiceImpl inviteServiceImpl;
	
	@RequestMapping(value = "/getRestaurants")
	public MessageWithObj getAllRestaurants() {
		
		return new MessageWithObj("Svi restorani", true, restaurantServiceImpl.getAllRestaurants());
	}
	
	@RequestMapping(value = "/getAvailableDesks/{starts}/{ends}/{idrest}")
	public MessageWithObj getAvailableDesks(@PathVariable("starts") String starts,
			@PathVariable("ends") String ends, @PathVariable("idrest") String idrest){
		
		System.out.println(Long.parseLong(starts));
		System.out.println(Long.parseLong(ends));
		System.out.println(idrest);
		
		long idresta = Long.parseLong(idrest);
		
		long d= Long.parseLong(starts);
		java.sql.Timestamp stamp = new java.sql.Timestamp(d);
		  Date date = new Date(stamp.getTime());
		  
		  SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		  String sqlstart = formatter.format(date);
		  
		  d= Long.parseLong(ends);
		  stamp = new java.sql.Timestamp(d);
		  date = new Date(stamp.getTime());
		  formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		  String sqlend = formatter.format(date);
		  
		  System.out.println(sqlstart);
		  System.out.println(sqlend);
		  
		 
		return new MessageWithObj("banned tables", true, tableServiceImpl.getBannedTables(idresta, sqlstart, sqlend));
	}
	
	@RequestMapping(value = "/getAllDesks/{idrest}")
	public MessageWithObj getAllDesksForRest(@PathVariable("idrest") String idrest){
		
		long id = Long.parseLong(idrest);
		
		return new MessageWithObj("stolovi", true, tableServiceImpl.getTablesByRestId(id));
	}
	
	@Transactional
	@RequestMapping(value = "/reserveTables/{start}/{end}/{arrt}/{restid}")
	public MessageWithObj reserveTables(@PathVariable("start") String start,
			@PathVariable("end") String end, @PathVariable("arrt") String[] tables,
			@PathVariable("restid") String restid,
			HttpServletRequest req){
		
		long restoranID = Long.parseLong(restid);
		
		long id1 = ((Person)req.getSession().getAttribute("guest")).getId();
		
		long d= Long.parseLong(start);
		java.sql.Timestamp stamp = new java.sql.Timestamp(d);
		  Date date = new Date(stamp.getTime());
		  
		  SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		  String sqlstart = formatter.format(date);
		  
		  d= Long.parseLong(end);
		  stamp = new java.sql.Timestamp(d);
		  date = new Date(stamp.getTime());
		  formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		  String sqlend = formatter.format(date);
		
		//poziv samom sebi
			inviteServiceImpl.inviteMeWhenReserve(id1,id1,sqlstart, sqlend, restoranID);
		
		for(int i=0; i<tables.length; i++){
			
			reservationServiceImpl.reserveOneTable(sqlstart, sqlend,  Integer.parseInt(tables[i]), id1);
		}

		return null;
	}
	
	@Transactional
	@RequestMapping(value = "/invite/{start}/{end}/{to}/{rest}")
	public MessageWithObj inviteFriend(@PathVariable("start") String start,
			@PathVariable("end") String end, @PathVariable("to") String to,@PathVariable("rest") String rest,
			HttpServletRequest req){
		
		long kome = Long.parseLong(to);
		
		long restid = Long.parseLong(rest);
		
		String msg="Poziv od: ";
		
long id1 = ((Person)req.getSession().getAttribute("guest")).getId();
String name = ((Person)req.getSession().getAttribute("guest")).getName();
String surname = ((Person)req.getSession().getAttribute("guest")).getSurname();
		
		long d= Long.parseLong(start);
		java.sql.Timestamp stamp = new java.sql.Timestamp(d);
		  Date date = new Date(stamp.getTime());
		  
		  SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		  String sqlstart = formatter.format(date);
		  
		  d= Long.parseLong(end);
		  stamp = new java.sql.Timestamp(d);
		  date = new Date(stamp.getTime());
		  formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		  String sqlend = formatter.format(date);
		  
		  msg+=name+" "+surname+". Pocetak: "+sqlstart+" Kraj: "+sqlend+". link: http://localhost:8080/restaurant/accept/";
		
		inviteServiceImpl.inviteOneFriend(id1, Long.parseLong(to), sqlstart, sqlend, restid);
		
		long invid = inviteServiceImpl.getIdInvitation(id1, kome, sqlstart, sqlend, restid);
		
		msg+=invid;
		SendMail sm = new SendMail("stefandvlp@gmail.com",msg );
		return new MessageWithObj("pozvan prijatelj" ,true, null);
		
	}
	
	@RequestMapping(value = "/myinvitations")
	public MessageWithObj getMyInvitations(HttpServletRequest req){
		
		long me = ((Person)req.getSession().getAttribute("guest")).getId();
		
		return new MessageWithObj("pozvi", true, inviteServiceImpl.getMyInvites(me));
	}
	@Transactional
	@RequestMapping(value = "/accept/{id}")
	public String acceptInv(@PathVariable("id") String id){
		
		
		long id1 = Long.parseLong(id);
		inviteServiceImpl.acceptOneInvitation(id1);
		
		return "Prihvatili ste poziv uspesno!";
	}
	
	
	@Transactional
	@RequestMapping(value = "/acceptinv/{id}")
	public void acceptInvitaition(@PathVariable("id") String id){
		
		long id1 = Long.parseLong(id);
		
		inviteServiceImpl.acceptOneInvitation(id1);

	}
	@Transactional
	@RequestMapping(value = "/cancelinv/{id}")
	public void cancelInvitation(@PathVariable("id") String id){
		
		long id1 = Long.parseLong(id);
		
		inviteServiceImpl.cancelOneInvitation(id1);
		
		
		
	}
	
	@RequestMapping(value = "/getOrderTables/{start}/{end}/{rezervant}")
	public MessageWithObj getTablesForOrder(@PathVariable("rezervant") String rezervant, @PathVariable("start") String start,
			@PathVariable("end") String end, HttpServletRequest req){
		
		long id = Long.parseLong(rezervant);
		
		long d= Long.parseLong(start);
		java.sql.Timestamp stamp = new java.sql.Timestamp(d);
		  Date date = new Date(stamp.getTime());
		  
		  SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		  String sqlstart = formatter.format(date);
		  
		  d= Long.parseLong(end);
		  stamp = new java.sql.Timestamp(d);
		  date = new Date(stamp.getTime());
		  formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		  String sqlend = formatter.format(date);
		  
		 ArrayList<Integer> rezervisaniStolovi= reservationServiceImpl.vratiStoloveDaNarucim(sqlstart, sqlend, id);
		 
		for(int i=0; i<rezervisaniStolovi.size(); i++){
			System.out.println(rezervisaniStolovi.get(i));
		}
		req.getSession().setAttribute("stoloviRezervacije",rezervisaniStolovi );
		return new MessageWithObj("stolovi za taj termin", true, rezervisaniStolovi);
		
	}
	
	
	@RequestMapping(value = "/getTablesOfReservation")
	public MessageWithObj getTablesForOrder(HttpServletRequest req){
		ArrayList<Integer> st= (ArrayList<Integer>) req.getSession().getAttribute("stoloviRezervacije");
		if(st!=null){
			return new MessageWithObj("Stolovi za taj termin", true, st);
		}else return new MessageWithObj("Greska: nema stolova za taj termin",false,null);
	}
	
	
	
	
	
}
