package com.restaurant.controller;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.restaurant.miscel.DrinkMeal;
import com.restaurant.miscel.MessageWithObj;
import com.restaurant.miscel.SendMail;
import com.restaurant.model.Drink;
import com.restaurant.model.Guest;
import com.restaurant.model.Meal;
import com.restaurant.model.Order;
import com.restaurant.model.OrderedDrink;
import com.restaurant.model.OrderedMeal;
import com.restaurant.model.Person;
import com.restaurant.model.Table;
import com.restaurant.service.DrinkServiceImpl;
import com.restaurant.service.InviteServiceImpl;
import com.restaurant.service.MealServiceImpl;
import com.restaurant.service.OrderServiceImpl;
import com.restaurant.service.OrderedDrinkServiceImpl;
import com.restaurant.service.OrderedMealServiceImpl;
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
	MealServiceImpl mealServiceImpl;
	
	@Autowired
	ReservationServiceImpl reservationServiceImpl;
	
	@Autowired
	InviteServiceImpl inviteServiceImpl;
	
	@Autowired
	DrinkServiceImpl drinkServiceImpl;
	@Autowired
	OrderedMealServiceImpl orderedMealServiceImpl;
	@Autowired
	OrderedDrinkServiceImpl orderedDrinkServiceImpl;
	@Autowired
	OrderServiceImpl orderServiceImpl;
	@RequestMapping(value = "/getRestaurants")
	public MessageWithObj getAllRestaurants(HttpServletRequest req) {
		
		long id = ((Guest)req.getSession().getAttribute("guest")).getId();
		
		return new MessageWithObj("Svi restorani", true, restaurantServiceImpl.getAllRestaurants());
	}
	
	@RequestMapping(value = "/getRestaurantsHistory")
	public MessageWithObj getAllRestaurantsHistory(HttpServletRequest req) {
		
		long id = ((Guest)req.getSession().getAttribute("guest")).getId();
		
		return new MessageWithObj("Svi restorani", true, restaurantServiceImpl.getForHistory(id));
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
	@RequestMapping(value = "/addRestToSession/{restid}")
	public MessageWithObj addRestorantoSession(@PathVariable("restid") String restid,
			HttpServletRequest req){
		req.getSession().setAttribute("restoran", Long.parseLong(restid));
		
		return new MessageWithObj("restoran temp u sesiji", true,(long)req.getSession().getAttribute("restoran"));
		
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
	
	@RequestMapping(value = "/addOrder", method = RequestMethod.POST)
	public MessageWithObj addOrder(@RequestBody DrinkMeal drinkMeal, HttpServletRequest req){

		long restid = (long)req.getSession().getAttribute("restoran");
		if(req.getSession().getAttribute("guest") instanceof Guest ){

			Guest waiter = (Guest) req.getSession().getAttribute("guest");
			
			Table table = tableServiceImpl.findOneTable(drinkMeal.getTableId());
			
			Order order = new Order();
			
			order.setTable(table);
			
			orderServiceImpl.save(order);
			
			int br = 0;
			
			if(drinkMeal.getDrinks() != null){
				for(String temp : drinkMeal.getDrinks()){
					Drink drink = drinkServiceImpl.findByName(temp,restid );
					if(drink == null){
						continue;
					}
					OrderedDrink od = new OrderedDrink();
					od.setDrink(drink);
					od.setOrder(order);
					od.setReady(0);
					od.setQuantity(drinkMeal.getQuantityDrinks().get(br));
					orderedDrinkServiceImpl.save(od);
					br++;
				}
			}
			//if(br == 0){
			//	orderServiceImpl.delete(order.getId());
			//}
			
			int br2 = 0;
			
			if(drinkMeal.getMeals() != null){
				for(String temp : drinkMeal.getMeals()){
					Meal meal = mealServiceImpl.findByName(temp,restid);
					if(meal == null){
						continue;
					}
					OrderedMeal om = new OrderedMeal();
					om.setMeal(meal);
					om.setOrder(order);
					om.setReady(0);
					om.setAcceptedMeal(0);
					//om.setQuantity(3);
					om.setQuantity(drinkMeal.getQuantityMeals().get(br2));
					orderedMealServiceImpl.save(om);
					br2++;
				}	
			}
			if(br == 0 && br2==0 ){
				orderServiceImpl.delete(order.getId());
			}
			return new MessageWithObj("Order added", true, null);
		}
		return new MessageWithObj("Order not added", false, null);
	}
	
	
	@RequestMapping(value = "/getTablesOfReservation")
	public MessageWithObj getTablesForOrder(HttpServletRequest req){
		ArrayList<Integer> st= (ArrayList<Integer>) req.getSession().getAttribute("stoloviRezervacije");
		if(st!=null){
			return new MessageWithObj("Stolovi za taj termin", true, st);
		}else return new MessageWithObj("Greska: nema stolova za taj termin",false,null);
	}
	
	
	
	
	
}
