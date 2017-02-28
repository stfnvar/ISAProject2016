package com.restaurant.controller;


import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.restaurant.miscel.MessageWithObj;
import com.restaurant.model.Administrator;
import com.restaurant.model.Bartender;
import com.restaurant.model.Cook;
import com.restaurant.model.Drink;
import com.restaurant.model.DrinkCard;
import com.restaurant.model.Meal;
import com.restaurant.model.Menu;
import com.restaurant.model.Person;
import com.restaurant.model.Restaurant;
import com.restaurant.model.RestaurantManager;
import com.restaurant.model.RestaurantSegment;
import com.restaurant.model.Table;
import com.restaurant.model.Waiter;
import com.restaurant.model.Worker;
import com.restaurant.model.WorkingSchedule;
import com.restaurant.service.AdminServiceImpl;
import com.restaurant.service.PersonServiceImpl;
import com.restaurant.service.RestaurantManagerServiceImpl;

@RestController
@Controller
@RequestMapping("/restaurantManager")
public class RestaurantManagerController {

	@Autowired
	private RestaurantManagerServiceImpl restaurantManagerServiceImpl;

	@Autowired
	private PersonServiceImpl personServiceImpl;

	@Autowired
	private AdminServiceImpl adminServiceImpl;

	@RequestMapping(method = RequestMethod.GET, value = "/menu")
	@ResponseBody
	public Set<Menu> getMenu(String stringId, HttpServletRequest req) {
		Long id = null;
		if (stringId == null)
			id = new Long(((RestaurantManager) req.getSession().getAttribute("guest")).getRestaurant().getId());
		else
			id = new Long(stringId);
		return restaurantManagerServiceImpl.getRestaurantMenu(id);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/menuMeals")
	@ResponseBody
	public ResponseEntity<Set<Meal>> getMenuMeals(@RequestParam("id") String ids, HttpServletRequest req) {
		Long id = new Long(ids);

		if (req.getSession().getAttribute("guest") instanceof RestaurantManager) {
			RestaurantManager rm = (RestaurantManager) req.getSession().getAttribute("guest");
			Long restaurantId = rm.getRestaurant().getId();
			Menu card = restaurantManagerServiceImpl.getRestaurantMenuSpecific(restaurantId, id);
			if (card != null)
				return new ResponseEntity<Set<Meal>>(restaurantManagerServiceImpl.getMenuMeals(id), HttpStatus.OK);
			else {
				return new ResponseEntity<Set<Meal>>(HttpStatus.UNAUTHORIZED);
			}
		}
		return new ResponseEntity<Set<Meal>>(HttpStatus.UNAUTHORIZED);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/drinkCard")
	@ResponseBody
	public Set<DrinkCard> getDrinkCard(String stringId, HttpServletRequest req) {
		Long id = null;
		if (stringId == null)
			id = new Long(((RestaurantManager) req.getSession().getAttribute("guest")).getRestaurant().getId());
		else
			id = new Long(stringId);
		return restaurantManagerServiceImpl.getRestaurantDrinkCard(id);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/drinks")
	@ResponseBody
	public ResponseEntity<Set<Drink>> getDrinkCardDrinks(@RequestParam("id") String ids, HttpServletRequest req,
			HttpServletResponse resp) throws IOException {
		Long id = new Long(ids);
		if (req.getSession().getAttribute("guest") instanceof RestaurantManager) {
			RestaurantManager rm = (RestaurantManager) req.getSession().getAttribute("guest");
			Long restaurantId = rm.getRestaurant().getId();
			DrinkCard card = restaurantManagerServiceImpl.getRestaurantDrinkCardSpecific(restaurantId, id);
			if (card != null)
				return new ResponseEntity<Set<Drink>>(restaurantManagerServiceImpl.getDrinkCardDrinks(id),
						HttpStatus.OK);
			else {
			}
		}
		return new ResponseEntity<Set<Drink>>(HttpStatus.UNAUTHORIZED);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/applyProfileChanges", consumes = MediaType.APPLICATION_JSON_VALUE)
	@Transactional
	public ResponseEntity<Person> applyProfileChanges(@RequestBody Person restaurantManager, HttpServletRequest req) {
		Person person = restaurantManagerServiceImpl.update(restaurantManager.getName(), restaurantManager.getSurname(),
				restaurantManager.getId());
		req.getSession().setAttribute("guest", person);
		return new ResponseEntity<Person>(personServiceImpl.findOne(restaurantManager.getId()), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/changePassword", consumes = MediaType.APPLICATION_JSON_VALUE)
	@Transactional
	public ResponseEntity<String> changePassword(@RequestBody ArrayList<String> params, HttpServletRequest req) {
		Person restaurantManager = (Person) req.getSession().getAttribute("guest");
		if (params.get(1) == null || params.get(2) == null) {
			return new ResponseEntity<String>("{\"status\": \"minLengthError\"}", HttpStatus.OK);
		}
		if (!params.get(1).equals(params.get(2))) {
			return new ResponseEntity<String>("{\"status\": \"notMatch\"}", HttpStatus.OK);
		}
		if (!restaurantManager.getPassword().equals(params.get(0))) {
			return new ResponseEntity<String>("{\"status\" :\"noAccess\"}", HttpStatus.OK);
		}
		restaurantManagerServiceImpl.changePassword(restaurantManager.getId(), params.get(1));
		return new ResponseEntity<String>("{\"status\" : \"changed\"}", HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/removeMeal", consumes = MediaType.APPLICATION_JSON_VALUE)
	@Transactional
	public MessageWithObj removeMeal(@RequestBody Meal meal) {

		restaurantManagerServiceImpl.removeMenuMeal(meal.getId());

		return new MessageWithObj("ok", true, null);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/modifyMeal", consumes = MediaType.APPLICATION_JSON_VALUE)
	@Transactional
	public MessageWithObj modifyMeal(@RequestBody Meal meal) {
		restaurantManagerServiceImpl.modifyMenuMeal(meal);
		return new MessageWithObj("ok", true, null);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/addMeal", consumes = MediaType.APPLICATION_JSON_VALUE)
	@Transactional
	public HttpStatus addMeal(@RequestBody Meal meal, HttpServletRequest req) {
		if (req.getSession().getAttribute("guest") instanceof RestaurantManager) {
			RestaurantManager rm = (RestaurantManager) req.getSession().getAttribute("guest");
			Long restaurantId = rm.getRestaurant().getId();
			Menu card = restaurantManagerServiceImpl.getRestaurantMenuSpecific(restaurantId, meal.getMenu().getId());
			if (card != null) {
				restaurantManagerServiceImpl.addMenuMeal(meal);
				return HttpStatus.OK;
			} else {
				return HttpStatus.UNAUTHORIZED;
			}
		}
		return HttpStatus.UNAUTHORIZED;
	}

	@RequestMapping(method = RequestMethod.POST, value = "/removeDrink", consumes = MediaType.APPLICATION_JSON_VALUE)
	@Transactional
	public MessageWithObj removeDrink(@RequestBody Drink meal) {

		restaurantManagerServiceImpl.removeDrink(meal.getId());

		return new MessageWithObj("ok", true, null);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/modifyDrink", consumes = MediaType.APPLICATION_JSON_VALUE)
	@Transactional
	public MessageWithObj modifyDrink(@RequestBody Drink meal) {
		restaurantManagerServiceImpl.modifyDrink(meal);
		return new MessageWithObj("ok", true, null);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/addDrink", consumes = MediaType.APPLICATION_JSON_VALUE)
	@Transactional
	public HttpStatus addDrking(@RequestBody Drink meal, HttpServletRequest req) {
		if (req.getSession().getAttribute("guest") instanceof RestaurantManager) {
			RestaurantManager rm = (RestaurantManager) req.getSession().getAttribute("guest");
			Long restaurantId = rm.getRestaurant().getId();
			DrinkCard card = restaurantManagerServiceImpl.getRestaurantDrinkCardSpecific(restaurantId,
					meal.getDrinkCard().getId());
			if (card != null) {
				restaurantManagerServiceImpl.addDrink(meal);
				return HttpStatus.OK;
			} else {
				return HttpStatus.UNAUTHORIZED;
			}
		}
		return HttpStatus.UNAUTHORIZED;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/tables")
	public ResponseEntity<Set<Table>> getTables(HttpServletRequest req) {
		if (req.getSession().getAttribute("guest") instanceof RestaurantManager) {
			RestaurantManager rm = (RestaurantManager) req.getSession().getAttribute("guest");
			Set<Table> set = restaurantManagerServiceImpl.findTablesInRestaurant(rm.getRestaurant().getId());
			return new ResponseEntity<Set<Table>>(set, HttpStatus.OK);
		}
		return new ResponseEntity<Set<Table>>(HttpStatus.UNAUTHORIZED);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/restaurantSegments")
	public ResponseEntity<Set<RestaurantSegment>> getSegments(HttpServletRequest req) {
		if (req.getSession().getAttribute("guest") instanceof RestaurantManager) {
			RestaurantManager rm = (RestaurantManager) req.getSession().getAttribute("guest");
			Set<RestaurantSegment> set = restaurantManagerServiceImpl.getRestaurantSegments(rm.getRestaurant().getId());
			return new ResponseEntity<Set<RestaurantSegment>>(set, HttpStatus.OK);
		}
		return new ResponseEntity<Set<RestaurantSegment>>(HttpStatus.UNAUTHORIZED);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/segmentTables", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Set<Table>> getSegmentTables(@RequestBody RestaurantSegment rs, HttpServletRequest req) {
		if (req.getSession().getAttribute("guest") instanceof RestaurantManager) {
			Set<Table> set = restaurantManagerServiceImpl.findTablesInSegment(rs.getId());
			return new ResponseEntity<Set<Table>>(set, HttpStatus.OK);
		}
		return new ResponseEntity<Set<Table>>(HttpStatus.UNAUTHORIZED);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/removeTable", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void removeTable(@RequestBody Table table, HttpServletRequest req) {
		if (req.getSession().getAttribute("guest") instanceof RestaurantManager) {
			restaurantManagerServiceImpl.removeTable(table.getId());
		}
	}

	@RequestMapping(method = RequestMethod.POST, value = "/removeSegment", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void removeSegment(@RequestBody RestaurantSegment segment, HttpServletRequest req) {
		if (req.getSession().getAttribute("guest") instanceof RestaurantManager) {
			restaurantManagerServiceImpl.removeSegment(segment.getId());
		}
	}

	@Transactional
	@RequestMapping(method = RequestMethod.POST, value = "/modifyTable", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void modifyTable(@RequestBody Table table, HttpServletRequest req) {
		if (req.getSession().getAttribute("guest") instanceof RestaurantManager) {
			restaurantManagerServiceImpl.modifyTable(table);
		}
	}

	@Transactional
	@RequestMapping(method = RequestMethod.POST, value = "/modifySegment", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void modifySegment(@RequestBody RestaurantSegment segment, HttpServletRequest req) {
		if (req.getSession().getAttribute("guest") instanceof RestaurantManager) {
			restaurantManagerServiceImpl.modifySegment(segment);
		}
	}

	@Transactional
	@RequestMapping(method = RequestMethod.POST, value = "/addTable", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void addTable(@RequestBody Table table, HttpServletRequest req) {
		if (req.getSession().getAttribute("guest") instanceof RestaurantManager) {
			RestaurantManager rm = (RestaurantManager) req.getSession().getAttribute("guest");
			table.setRestaurant(rm.getRestaurant());
			restaurantManagerServiceImpl.addTable(table);
		}
	}

	@Transactional
	@RequestMapping(method = RequestMethod.POST, value = "/addSegment", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void addSegment(@RequestBody RestaurantSegment segment, HttpServletRequest req) {
		if (req.getSession().getAttribute("guest") instanceof RestaurantManager) {
			RestaurantManager rm = (RestaurantManager) req.getSession().getAttribute("guest");
			segment.getRestaurant().setId(rm.getRestaurant().getId());
			restaurantManagerServiceImpl.addSegment(segment);
		}
	}

	@RequestMapping(method = RequestMethod.GET, value = "/getWaiters")
	public ResponseEntity<Set<Waiter>> getStaffWaiters(HttpServletRequest req) {
		if (req.getSession().getAttribute("guest") instanceof RestaurantManager) {
			RestaurantManager rm = (RestaurantManager) req.getSession().getAttribute("guest");
			return new ResponseEntity<Set<Waiter>>(restaurantManagerServiceImpl.getWaiters(rm.getRestaurant().getId()),
					HttpStatus.OK);
		}
		return new ResponseEntity<Set<Waiter>>(HttpStatus.UNAUTHORIZED);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/getBartenders")
	public ResponseEntity<Set<Bartender>> getStaffBartenders(HttpServletRequest req) {
		if (req.getSession().getAttribute("guest") instanceof RestaurantManager) {
			RestaurantManager rm = (RestaurantManager) req.getSession().getAttribute("guest");
			return new ResponseEntity<Set<Bartender>>(
					restaurantManagerServiceImpl.getBartenders(rm.getRestaurant().getId()), HttpStatus.OK);
		}
		return new ResponseEntity<Set<Bartender>>(HttpStatus.UNAUTHORIZED);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/getCooks")
	public ResponseEntity<Set<Cook>> getStaffCooks(HttpServletRequest req) {
		if (req.getSession().getAttribute("guest") instanceof RestaurantManager) {
			RestaurantManager rm = (RestaurantManager) req.getSession().getAttribute("guest");
			restaurantManagerServiceImpl.getWaiters(rm.getId());
			return new ResponseEntity<Set<Cook>>(restaurantManagerServiceImpl.getCooks(rm.getRestaurant().getId()),
					HttpStatus.OK);
		}
		return new ResponseEntity<Set<Cook>>(HttpStatus.UNAUTHORIZED);
	}

	@Transactional
	@RequestMapping(method = RequestMethod.POST, value = "/addCook", consumes = MediaType.APPLICATION_JSON_VALUE)
	public MessageWithObj addCook(@RequestBody Cook cook, HttpServletRequest req) {
		if (req.getSession().getAttribute("guest") instanceof RestaurantManager) {
			RestaurantManager rm = (RestaurantManager) req.getSession().getAttribute("guest");
			if (rm.getRestaurant().getId() == cook.getRestaurant().getId()) {
				if (personServiceImpl.findOneByEmail(cook.getEmail()) == null) {
					restaurantManagerServiceImpl.addCook(cook);
					return new MessageWithObj("Created", true, null);
				}
				return new MessageWithObj("Email already exists", false, null);
			}
			return new MessageWithObj("Unauthorized attempt", false, null);
		}
		return new MessageWithObj("Unauthorized attempt", false, null);
	}

	@Transactional
	@RequestMapping(method = RequestMethod.POST, value = "/addWaiter", consumes = MediaType.APPLICATION_JSON_VALUE)
	public MessageWithObj addWaiter(@RequestBody Waiter waiter, HttpServletRequest req) {
		if (req.getSession().getAttribute("guest") instanceof RestaurantManager) {
			RestaurantManager rm = (RestaurantManager) req.getSession().getAttribute("guest");
			if (waiter.getRestaurant().getId() == rm.getRestaurant().getId()) {
				if (personServiceImpl.findOneByEmail(waiter.getEmail()) == null) {
					restaurantManagerServiceImpl.addWaiter(waiter);
					return new MessageWithObj("Created", true, null);
				}
				return new MessageWithObj("Email already exists", false, null);
			}
			return new MessageWithObj("Unauthorized attempt", false, null);
		}
		return new MessageWithObj("Unauthorized attempt", false, null);
	}

	@Transactional
	@RequestMapping(method = RequestMethod.POST, value = "/addBartender", consumes = MediaType.APPLICATION_JSON_VALUE)
	public MessageWithObj addCook(@RequestBody Bartender bartender, HttpServletRequest req) {
		if (req.getSession().getAttribute("guest") instanceof RestaurantManager) {
			RestaurantManager rm = (RestaurantManager) req.getSession().getAttribute("guest");
			if (bartender.getRestaurant().getId() == rm.getRestaurant().getId()) {
				if (personServiceImpl.findOneByEmail(bartender.getEmail()) == null) {
					restaurantManagerServiceImpl.addBartender(bartender);
					return new MessageWithObj("Created", true, null);
				}
				return new MessageWithObj("Email already exists", false, null);
			}
			return new MessageWithObj("Unauthorized attempt", false, null);
		}
		return new MessageWithObj("Unauthorized attempt", false, null);
	}
	
	
	@Transactional
	@RequestMapping(method = RequestMethod.POST, value="/removeWorker", consumes = MediaType.APPLICATION_JSON_VALUE)
	public MessageWithObj removeWorker(@RequestBody Cook cook, HttpServletRequest req) {
		if (req.getSession().getAttribute("guest") instanceof RestaurantManager) {
			RestaurantManager rm = (RestaurantManager) req.getSession().getAttribute("guest");
			if (cook.getRestaurant().getId() == rm.getRestaurant().getId()) {
				if (personServiceImpl.findOneByEmail(cook.getEmail()) == null) {
					restaurantManagerServiceImpl.removeWorker(cook.getId());
					return new MessageWithObj("Created", true, null);
				}
				return new MessageWithObj("Cook doesn't exist", false, null);
			}
			return new MessageWithObj("Unauthorized attempt", false, null);
		}
		return new MessageWithObj("Unauthorized attempt", false, null);
	}	

	@RequestMapping(method = RequestMethod.POST, value = "/getOnDutyDay", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Set<WorkingSchedule>> getOnDutyDay(@RequestBody String s, HttpServletRequest req) {
		s = s.replace("\"", "");
		Date date = parseDate(s);
		if (req.getSession().getAttribute("guest") instanceof RestaurantManager) {
			return new ResponseEntity<Set<WorkingSchedule>>(restaurantManagerServiceImpl.getOnDuty(date),
					HttpStatus.OK);
		}
		return new ResponseEntity<Set<WorkingSchedule>>(HttpStatus.UNAUTHORIZED);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/getAdmins")
	public ResponseEntity<List<Administrator>> getAdministrators(HttpServletRequest req) {
		if (req.getSession().getAttribute("guest") instanceof Administrator) {
			Administrator admin = (Administrator) req.getSession().getAttribute("guest");
			if (admin.isSupreme())
				return new ResponseEntity<List<Administrator>>(restaurantManagerServiceImpl.getAdministrators(),
						HttpStatus.OK);
		}
		return new ResponseEntity<List<Administrator>>(HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/removeAdmin", consumes = MediaType.APPLICATION_JSON_VALUE)
	public MessageWithObj removeAdmin(HttpServletRequest req, @RequestBody Administrator admin) {
		if (req.getSession().getAttribute("guest") instanceof Administrator) {
			Administrator aa = (Administrator) req.getSession().getAttribute("guest");
			if (aa.isSupreme() && (aa.getId() != admin.getId()) ) {
				restaurantManagerServiceImpl.removeAdmin(admin);
				return new MessageWithObj("Removed", true, null);
			}
		}
		return new MessageWithObj("Not authorized", false, null);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/modifyAdmin", consumes = MediaType.APPLICATION_JSON_VALUE)
	@Transactional
	public MessageWithObj modifyAdmin(HttpServletRequest req, @RequestBody Administrator admin) {
		if (req.getSession().getAttribute("guest") instanceof Administrator) {
			Administrator aa = (Administrator) req.getSession().getAttribute("guest");
			if (aa.isSupreme()) {
				if (personServiceImpl.findOneByEmail(admin.getEmail()) != null) {
					admin.setSupreme(false);
					System.out.println(admin.getName());
					restaurantManagerServiceImpl.modifyAdmin(admin);
					return new MessageWithObj("Modified", true, null);
				} else {
					return new MessageWithObj("Account doesn't exists", false, null);
				}
			}
		}
		return new MessageWithObj("Not authorized", false, null);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/addAdmin", consumes = MediaType.APPLICATION_JSON_VALUE)
	public MessageWithObj addAdmin(HttpServletRequest req, @RequestBody Administrator admin) {
		if (req.getSession().getAttribute("guest") instanceof Administrator) {
			Administrator aa = (Administrator) req.getSession().getAttribute("guest");
			if (aa.isSupreme()) {
				if (personServiceImpl.findOneByEmail(admin.getEmail()) == null) {
					admin.setSupreme(false);
					restaurantManagerServiceImpl.createAdmin(admin);
					return new MessageWithObj("Created", true, null);
				} else {
					return new MessageWithObj("Exists", false, null);
				}
			}
		}
		return new MessageWithObj("Not authorized", false, null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/getAdminRestaurants")
	public ResponseEntity<List<Restaurant>> addAdmin(HttpServletRequest req) {
		if (req.getSession().getAttribute("guest") instanceof Administrator) {
			return new ResponseEntity<List<Restaurant>>(restaurantManagerServiceImpl.findAdminRestaurants(),
					HttpStatus.OK);
		}
		return new ResponseEntity<List<Restaurant>>(HttpStatus.UNAUTHORIZED);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/removeRestaurant", consumes = MediaType.APPLICATION_JSON_VALUE)
	public MessageWithObj removeRestaurant(HttpServletRequest req, @RequestBody Restaurant restaurant) {
		if (req.getSession().getAttribute("guest") instanceof Administrator) {
			adminServiceImpl.removeRestaurant(restaurant.getId());
			return new MessageWithObj("Removed", true, null);
		}
		return new MessageWithObj("Not authorized", false, null);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/addRestaurant", consumes = MediaType.APPLICATION_JSON_VALUE)
	public MessageWithObj addRestaurant(HttpServletRequest req, @RequestBody Restaurant restaurant) {
		if (req.getSession().getAttribute("guest") instanceof Administrator) {
			adminServiceImpl.addRestaurant(restaurant);
			return new MessageWithObj("Created", true, null);
		}
		return new MessageWithObj("Not authorized", false, null);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/addManager", consumes = MediaType.APPLICATION_JSON_VALUE)
	public MessageWithObj addManager(HttpServletRequest req, @RequestBody RestaurantManager rm) {
		if (req.getSession().getAttribute("guest") instanceof Administrator) {
			restaurantManagerServiceImpl.addRestaurantManager(rm);
			return new MessageWithObj("Created", true, null);
		}
		return new MessageWithObj("Not authorized", false, null);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/getRestaurantManagersAdmin", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Set<RestaurantManager>> getRestaurantManagersAdmin(HttpServletRequest req,
			@RequestBody Long id) {
		if (req.getSession().getAttribute("guest") instanceof Administrator) {

			return new ResponseEntity<Set<RestaurantManager>>(adminServiceImpl.getRestaurantManagerByRestaurantId(id),
					HttpStatus.OK);
		}
		return new ResponseEntity<Set<RestaurantManager>>(HttpStatus.UNAUTHORIZED);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/removeManager", consumes = MediaType.APPLICATION_JSON_VALUE)
	public MessageWithObj removeManager(HttpServletRequest req, @RequestBody RestaurantManager rm) {
		if (req.getSession().getAttribute("guest") instanceof Administrator) {
			restaurantManagerServiceImpl.removeManager(rm.getId());
			return new MessageWithObj("removed", true, null);
		}
		return new MessageWithObj("unauthorized", false, null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/getRating")
	public Double getRating(HttpServletRequest req) {
		if (req.getSession().getAttribute("guest") instanceof RestaurantManager) {
			RestaurantManager rm = (RestaurantManager) req.getSession().getAttribute("guest");
			return restaurantManagerServiceImpl.getRestaurantRating(rm.getRestaurant().getId());
		}
		return new Double(0);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/getWaiterRating", consumes = MediaType.APPLICATION_JSON_VALUE)
	public Double getWaiterRating(HttpServletRequest req, @RequestBody Waiter waiter) {
		if (req.getSession().getAttribute("guest") instanceof RestaurantManager) {
			RestaurantManager rm = (RestaurantManager) req.getSession().getAttribute("guest");
			return restaurantManagerServiceImpl.getWaiterRating(waiter.getName().toLowerCase(),
					waiter.getSurname().toLowerCase(), rm.getRestaurant().getId());
		}
		return new Double(0);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/getMealRating", consumes = MediaType.APPLICATION_JSON_VALUE)
	public Double getWaiterRating(HttpServletRequest req, @RequestBody Meal meal) {
		if (req.getSession().getAttribute("guest") instanceof RestaurantManager) {
			RestaurantManager rm = (RestaurantManager) req.getSession().getAttribute("guest");
			return restaurantManagerServiceImpl.getMealRating(meal.getName().toLowerCase(), rm.getRestaurant().getId());
		}
		return new Double(0);
	}

	public static Date parseDate(String date) {
		try {
			DateFormat f = new SimpleDateFormat("yyyy-MM-dd");
			return f.parse(date);
		} catch (ParseException e) {
			return null;
		}
	}

	@RequestMapping(method = RequestMethod.POST, value = "/getOnDuty", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Set<WorkingSchedule>> getOnDuty(@RequestBody String s, HttpServletRequest req) {
		s = s.replace("\"", "");
		String start = s.substring(0, 10);
		String end = s.substring(0, s.length());
		System.out.println(start);
		System.out.println(end);
		Date st = parseDate(start);
		Date en = parseDate(end);
		if (req.getSession().getAttribute("guest") instanceof RestaurantManager) {
			return new ResponseEntity<Set<WorkingSchedule>>(restaurantManagerServiceImpl.getOnDutyPeriod(st, en),
					HttpStatus.OK);

		}
		return new ResponseEntity<Set<WorkingSchedule>>(HttpStatus.UNAUTHORIZED);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/removeSchedule", consumes = MediaType.APPLICATION_JSON_VALUE)
	public MessageWithObj removeSchedule(@RequestBody WorkingSchedule ws, HttpServletRequest req) {
		if (req.getSession().getAttribute("guest") instanceof RestaurantManager) {
			restaurantManagerServiceImpl.removeSchedule(ws.getId());
			return new MessageWithObj("Removed", true, null);
		}
		return new MessageWithObj("Unauthorized", false, null);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/addSchedule", consumes = MediaType.APPLICATION_JSON_VALUE)
	public MessageWithObj addSchedule(@RequestBody WorkingSchedule ws, HttpServletRequest req) {
		if (req.getSession().getAttribute("guest") instanceof RestaurantManager) {
			if(ws.getStart().before(ws.getEnd())){
			restaurantManagerServiceImpl.saveSchedule(ws);
			return new MessageWithObj("Saved", true, null);
			}
			return new MessageWithObj("Starting date must be before ending date", false, null);
		}
		return new MessageWithObj("Unauthorized", false, null);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/getWorkers")
	public ResponseEntity<List<Worker>> getWorkers(HttpServletRequest req) {
		if (req.getSession().getAttribute("guest") instanceof RestaurantManager) {
			RestaurantManager rm = (RestaurantManager) req.getSession().getAttribute("guest");
			return new ResponseEntity<List<Worker>>(restaurantManagerServiceImpl.getWorkers(rm.getRestaurant().getId()),
					HttpStatus.OK);
		}
		return new ResponseEntity<List<Worker>>(HttpStatus.UNAUTHORIZED);
	}

}
