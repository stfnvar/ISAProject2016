package com.restaurant.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.json.HTTP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.restaurant.miscel.DrinkMeal;
import com.restaurant.miscel.MessageWithObj;
import com.restaurant.model.Bartender;
import com.restaurant.model.Bill;
import com.restaurant.model.Drink;
import com.restaurant.model.Meal;
import com.restaurant.model.Order;
import com.restaurant.model.OrderedDrink;
import com.restaurant.model.OrderedMeal;
import com.restaurant.model.Person;
import com.restaurant.model.Rating;
import com.restaurant.model.Restaurant;
import com.restaurant.model.RestaurantManager;
import com.restaurant.model.Table;
import com.restaurant.model.Waiter;
import com.restaurant.model.WorkingSchedule;
import com.restaurant.repository.OrderRepository;
import com.restaurant.service.BillServiceImpl;
import com.restaurant.service.DrinkServiceImpl;
import com.restaurant.service.MealServiceImpl;
import com.restaurant.service.OrderServiceImpl;
import com.restaurant.service.OrderedDrinkServiceImpl;
import com.restaurant.service.OrderedMealServiceImpl;
import com.restaurant.service.PersonServiceImpl;
import com.restaurant.service.RatingServiceImpl;
import com.restaurant.service.RestaurantManagerServiceImpl;
import com.restaurant.service.RestaurantServiceImpl;
import com.restaurant.service.TableServiceImpl;
import com.restaurant.service.WaiterServiceImpl;
import org.springframework.ui.Model;

@RestController
@RequestMapping("/waiter")
public class WaiterController {

	@Autowired
	WaiterServiceImpl waiterServiceImpl;
	
	@Autowired
	PersonServiceImpl personServiceImpl;
	
	@Autowired
	TableServiceImpl tableServiceImpl;
	
	@Autowired
	DrinkServiceImpl drinkServiceImpl;
	
	@Autowired
	OrderedDrinkServiceImpl orderedDrinkServiceImpl;
	
	@Autowired
	OrderedMealServiceImpl orderedMealServiceImpl;
	
	@Autowired
	MealServiceImpl mealServiceImpl;
	
	@Autowired
	OrderServiceImpl orderServiceImpl;
	
	@Autowired
	BillServiceImpl billServiceImpl;
	
	@Autowired
	RestaurantServiceImpl restaurantServiceImpl;
	
	@Autowired
	RatingServiceImpl ratingServiceImpl;
	
	@Autowired
	RestaurantManagerServiceImpl restaurantManagerServiceImpl;
	
	@RequestMapping(value = "/getMealRating", method = RequestMethod.POST)
	public Double getMealRating(HttpServletRequest req, @RequestBody Restaurant restaurant){
		
		Set<Meal> meals = mealServiceImpl.findByRestaurant(restaurant.getId());
		ArrayList<Meal> list = new ArrayList<Meal>();
		
		for (Meal w : meals) {
			list.add(w);
		}
		System.out.println("OOO"+list.size());
		if(list.isEmpty()){
			return null;
		}
		
		return restaurantManagerServiceImpl.getMealRating(list.get(0).getName().toLowerCase(), list.get(0).getId());
	}
	
	@Transactional
	@RequestMapping(value = "/giveRatingToMeal/{id}/{rating}", method = RequestMethod.POST)
	public Double giveRatingToMeal(@PathVariable("id") long id, @PathVariable("rating") double rating, HttpServletRequest req){

		System.out.println(id);
		Set<Meal> meals = mealServiceImpl.findByRestaurant(id);
		
		ArrayList<Meal> list = new ArrayList<Meal>();
		
		for (Meal w : meals) {
			list.add(w);
		}
		
		if(list.isEmpty()){
			return null;
		}
		
		for(int i = 0; i<list.size();i++){
			Rating r = new Rating();
			r.setMeal(list.get(i));
			r.setRating(rating);
			ratingServiceImpl.save(r);
		}
		
		return restaurantManagerServiceImpl.getMealRating(list.get(0).getName(), list.get(0).getId());
	}
	
	@RequestMapping(value = "/getWaiterRating", method = RequestMethod.POST)
	public Double getWaiterRating(HttpServletRequest req, @RequestBody Restaurant restaurant){
		
		Set<Waiter> waiters = restaurantManagerServiceImpl.getWaiters(restaurant.getId());
		
		ArrayList<Waiter> list = new ArrayList<Waiter>();
		
		for (Waiter w : waiters) {
			list.add(w);
		}
		
		if(list.isEmpty()){
			return null;
		}
		
		return restaurantManagerServiceImpl.getWaiterRating(list.get(0).getName(), list.get(0).getSurname(), list.get(0).getId());
	}
	
	@Transactional
	@RequestMapping(value = "/giveRatingToWaiter/{id}/{rating}", method = RequestMethod.POST)
	public Double giveRatingToWaiter(@PathVariable("id") long id, @PathVariable("rating") double rating, HttpServletRequest req){

		System.out.println(id);
		Set<Waiter> waiters = restaurantManagerServiceImpl.getWaiters(id);
		
		ArrayList<Waiter> list = new ArrayList<Waiter>();
		
		for (Waiter w : waiters) {
			list.add(w);
		}
		
		if(list.isEmpty()){
			return null;
		}
		
		for(int i = 0; i<list.size();i++){
			Rating r = new Rating();
			r.setWaiter(list.get(i));
			r.setRating(rating);
			ratingServiceImpl.save(r);
		}
		
		return restaurantManagerServiceImpl.getWaiterRating(list.get(0).getName(), list.get(0).getSurname(), list.get(0).getId());
	}
	
	
	@RequestMapping(value = "/getRestaurantRating", method = RequestMethod.POST)
	public Double getRestaurantRating(HttpServletRequest req, @RequestBody Restaurant restaurant){
		
		return restaurantManagerServiceImpl.getRestaurantRating(restaurant.getId());
	}
	
	@Transactional
	@RequestMapping(value = "/giveRatingToRestaurant/{id}/{rating}", method = RequestMethod.POST)
	public Double giveRatingToRestaurant(@PathVariable("id") long id, @PathVariable("rating") double rating, HttpServletRequest req){

		Restaurant restaurant = restaurantServiceImpl.findOne(id);
		
		Rating r = new Rating();
		r.setRestaurant(restaurant);
		r.setRating(rating);
		ratingServiceImpl.save(r);
		
		return restaurantManagerServiceImpl.getRestaurantRating(restaurant.getId());
	}
	
	
	
	public static Date parseDate(String date) {
		try {
			DateFormat f = new SimpleDateFormat("yyyy-MM-dd");
			return f.parse(date);
		} catch (ParseException e) {
			return null;
		}
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/getOnDutyDay", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Set<WorkingSchedule>> getOnDutyDay(@RequestBody String s, HttpServletRequest req) {
		
		Waiter w = (Waiter) req.getSession().getAttribute("guest");
		
		s = s.replace("\"", "");
		Date date = parseDate(s);
		if (req.getSession().getAttribute("guest") instanceof Waiter) {
			return new ResponseEntity<Set<WorkingSchedule>>(waiterServiceImpl.getOnDutyDay(date, w.getId()),
					HttpStatus.OK);
		}
		return new ResponseEntity<Set<WorkingSchedule>>(HttpStatus.UNAUTHORIZED);
	}
	
	@RequestMapping(value = "/checkFirstTime/{id}", method = RequestMethod.GET)
	public Waiter checkFirstTime(@PathVariable long id){
		System.out.println("USAO");
		Waiter w = waiterServiceImpl.findOneById(id);
		//System.out.println(w.getId());
		//System.out.println(w.getFirstTimeChangePW());
		if(w.getFirstTimeChangePW() == 0)
			return w;
		else
			return null;
	}
	
	@Transactional
	@RequestMapping(value = "/ftChangePW/{id}/{pw}", method = RequestMethod.POST)
	public Waiter ftChangePW(@PathVariable("id") long id, @PathVariable("pw") String pw, HttpServletRequest req){
		System.out.println("USPEO?");
		Waiter w = waiterServiceImpl.findOneById(id);
		
		waiterServiceImpl.changePW(w.getId(), pw);
		waiterServiceImpl.changeFT(w.getId());
		
		Person per = personServiceImpl.findOneByEmail(w.getEmail());
		
		System.out.println("ODAODSKADOASDOKDAO");
		System.out.println(pw);
		System.out.println(w.getPassword());
		System.out.println(per.getPassword());
		
		req.getSession().removeAttribute("guest");
		//req.getSession().setAttribute("guest", per);
		
		return w;
	}
	
	@Transactional
	@RequestMapping(value="/updateWaiter", method = RequestMethod.POST)
	public MessageWithObj updateWaiter(@RequestBody Person person, HttpServletRequest req){
		
		Person per = personServiceImpl.findOneByEmail(person.getEmail());

		waiterServiceImpl.updateOneWaiter(per.getId(), person.getName(), person.getSurname(),
				person.getEmail(), person.getPassword());
		
		per = personServiceImpl.findOneByEmail(person.getEmail());
		
		req.getSession().removeAttribute("guest");
		req.getSession().setAttribute("guest", per);
		
		System.out.println("APDEJTKONOBAR");
		return new MessageWithObj("Update completed", true, null);
	}
	
	@RequestMapping(value = "/addOrder", method = RequestMethod.POST)
	public MessageWithObj addOrder(@RequestBody DrinkMeal drinkMeal, HttpServletRequest req){
		
		if(req.getSession().getAttribute("guest") instanceof Waiter){

			Waiter waiter = (Waiter) req.getSession().getAttribute("guest");
			
			Table table = tableServiceImpl.findOneTable(drinkMeal.getTableId());
			
			Order order = new Order();
			
			order.setTable(table);
			
			orderServiceImpl.save(order);
			
			int br = 0;
			
			if(drinkMeal.getDrinks() != null){
				for(String temp : drinkMeal.getDrinks()){
					Drink drink = drinkServiceImpl.findByName(temp, waiter.getRestaurant().getId());
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
					Meal meal = mealServiceImpl.findByName(temp, waiter.getRestaurant().getId());
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
	
	@RequestMapping(value = "/getAllOrders", method = RequestMethod.GET)
	public ArrayList<Order> getAllOrders( HttpServletRequest req){	
		if(req.getSession().getAttribute("guest") instanceof Waiter){
			Waiter waiter = (Waiter) req.getSession().getAttribute("guest");
			
			return orderServiceImpl.findAllOrders(waiter.getRestaurant().getId());
		}
		return null;
	}
	
	
	@RequestMapping(value = "/getDetails", method = RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<OrderedDrink>> getDetails(HttpServletRequest req, @RequestBody Long id){
		if(req.getSession().getAttribute("guest") instanceof Waiter){
			return new ResponseEntity<List<OrderedDrink>>(orderedDrinkServiceImpl.findByOrderId(id), HttpStatus.OK);
		}
		return new ResponseEntity<List<OrderedDrink>>(HttpStatus.UNAUTHORIZED);
	}
	
	
	@RequestMapping(value = "/getDetailsMeals", method = RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<OrderedMeal>> getDetailsMeals(HttpServletRequest req, @RequestBody Long id){
		if(req.getSession().getAttribute("guest") instanceof Waiter){
			return new ResponseEntity<List<OrderedMeal>>(orderedMealServiceImpl.findByOrderId(id), HttpStatus.OK);
		}
		return new ResponseEntity<List<OrderedMeal>>(HttpStatus.UNAUTHORIZED);
	}

	
	@Transactional
	@RequestMapping(value="/editOrderDrinks", method = RequestMethod.POST)
	public MessageWithObj editOrderDrinks(@RequestBody ArrayList<OrderedDrink> od, HttpServletRequest req){
		
		if(req.getSession().getAttribute("guest") instanceof Waiter){

			Waiter waiter = (Waiter) req.getSession().getAttribute("guest");
				
			if(od != null){
				for(int i = 0; i < od.size(); i++){
					OrderedDrink oDrink = od.get(i);
					//Long drinkId = oDrink.getDrink().getId();
					Drink drink = drinkServiceImpl.findByName(oDrink.getDrink().getName(), waiter.getRestaurant().getId());
					Long drinkId = drink.getId();
					
					orderedDrinkServiceImpl.updateQuantity(oDrink.getQuantity(), oDrink.getId());
					System.out.println("IDPICA"+drinkId);
					System.out.println("GLAVNIID"+oDrink.getId());
					orderedDrinkServiceImpl.updateDrinkName(drinkId, oDrink.getId());
					
					System.out.println(oDrink.getDrink().getName());
					System.out.println(oDrink.getQuantity());
					System.out.println(oDrink.getId());
				}
			}
			return new MessageWithObj("Order drinks edited", true, null);
		}
		return new MessageWithObj("Order drinks fail edit", false, null);
	}
	
	@Transactional
	@RequestMapping(value="/editOrderMeals", method = RequestMethod.POST)
	public MessageWithObj editOrderMeals(@RequestBody ArrayList<OrderedMeal> od, HttpServletRequest req){
		
		if(req.getSession().getAttribute("guest") instanceof Waiter){
			
			Waiter waiter = (Waiter) req.getSession().getAttribute("guest");
			
			if(od != null){
				for(int i = 0; i < od.size(); i++){
					OrderedMeal oDrink = od.get(i);
					//Long drinkId = oDrink.getDrink().getId();
					Meal drink = mealServiceImpl.findByName(oDrink.getMeal().getName(), waiter.getRestaurant().getId());
					Long drinkId = drink.getId();
					
					orderedMealServiceImpl.updateQuantity(oDrink.getQuantity(), oDrink.getId());
					orderedMealServiceImpl.updateMealName(drinkId, oDrink.getId());
					
				}
			}
			
			return new MessageWithObj("Order meals edited", true, null);
		}
		return new MessageWithObj("Order meals fail edit", false, null);
 
	}
	
	
	@RequestMapping(value="/editOrder", method = RequestMethod.POST)
	public MessageWithObj editOrder(@RequestBody DrinkMeal drinkMeal){
		
		int br = 0;
		
		/*if(this.li == null){
			System.out.println("nal");
		} else
		{
			System.out.println("ne");
			for(int i =0; i < li.size(); i++){
				System.out.println(li.get(i));
			}
		}*/
		
		/*if(drinkMeal.getDrinks() != null){
			for(String temp : drinkMeal.getDrinks()){
				Drink drink = drinkServiceImpl.findByName(temp);
				OrderedDrink od = new OrderedDrink();
				od.setDrink(drink);
				od.setOrder(order);
				od.setReady(0);
				od.setQuantity(drinkMeal.getQuantityDrinks().get(br));
				orderedDrinkServiceImpl.save(od);
				br++;
			}
		}*/
		
		return new MessageWithObj("Order edited", true, null);
	}
	
	
	@Transactional
	@RequestMapping(value = "/createBill")
	public Bill createBill(@RequestBody Order order, HttpServletRequest req){
		
		if(req.getSession().getAttribute("guest") instanceof Waiter){
			
			Waiter waiter = (Waiter) req.getSession().getAttribute("guest");
			
			Bill bill = new Bill();
			bill.setOrder(order);
			bill.setWaiter(waiter);
			
			ArrayList<OrderedDrink> od = orderedDrinkServiceImpl.findByOrderId(order.getId());
			int totalPrice = 0;
			if(!od.isEmpty()){
				for(int i = 0; i < od.size(); i++){
					for(int j = 0; j < od.get(i).getQuantity(); j++){
						totalPrice += od.get(i).getDrink().getPrice();
					}
				}
			}
			
			ArrayList<OrderedMeal> om = orderedMealServiceImpl.findByOrderId(order.getId());
			if(!om.isEmpty()){
				for(int i = 0; i < om.size(); i++){
					for(int j = 0; j < om.get(i).getQuantity(); j++){
						totalPrice += om.get(i).getMeal().getPrice();
					}
				}
			}
			System.out.println(totalPrice);
			
			bill.setTotalPrice(totalPrice);
			billServiceImpl.save(bill);
			
				
			return bill;
		}
		
		return null;
	}
	
	
	
}
