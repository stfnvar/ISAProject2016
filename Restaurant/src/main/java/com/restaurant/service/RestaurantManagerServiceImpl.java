package com.restaurant.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
import com.restaurant.repository.AdminRepository;
import com.restaurant.repository.BartenderRepository;
import com.restaurant.repository.CookRepository;
import com.restaurant.repository.DrinkCardRepository;
import com.restaurant.repository.DrinkRepository;
import com.restaurant.repository.MealRepository;
import com.restaurant.repository.MenuRepository;
import com.restaurant.repository.PersonRepository;
import com.restaurant.repository.RatingRepository;
import com.restaurant.repository.RestaurantManagerRepository;
import com.restaurant.repository.RestaurantRepository;
import com.restaurant.repository.RestaurantSegmentRepository;
import com.restaurant.repository.TableRepository;
import com.restaurant.repository.WaiterRepository;
import com.restaurant.repository.WorkerRepository;
import com.restaurant.repository.WorkingScheduleRepository;

@Service
public class RestaurantManagerServiceImpl implements RestaurantManagerService {

	@Autowired
	AdminRepository adminRepository;

	@Autowired
	RestaurantRepository restaurantRepository;

	@Autowired
	RestaurantManagerRepository restaurantManagerRepository;

	@Autowired
	RestaurantSegmentRepository restaurantSegmentRepository;

	@Autowired
	MenuRepository menuRepository;

	@Autowired
	MealRepository mealRepository;

	@Autowired
	DrinkCardRepository drinkCardRepository;

	@Autowired
	DrinkRepository drinkRepository;

	@Autowired
	PersonRepository personRepository;

	@Autowired
	TableRepository tableRepository;

	@Autowired
	WaiterRepository waiterRepository;

	@Autowired
	CookRepository cookRepository;

	@Autowired
	BartenderRepository bartenderRepository;

	@Autowired
	WorkingScheduleRepository workingScheduleRepository;

	@Autowired
	RatingRepository ratingRepository;

	@Autowired
	WorkerRepository workingRepository;

	@Override
	public Restaurant getRestaurantProfile(Long id) {
		Restaurant restaurant = (Restaurant) restaurantRepository.findOne(id);
		return restaurant;
	}

	@Override
	public List<Worker> getWorkers(Long id) {
		return workingRepository.getWorkersRestaurant(id);
	}

	@Override
	public Set<Menu> getRestaurantMenu(Long id) {
		return menuRepository.findByRestaurant_Id(id);
	}

	@Override
	public Set<Meal> getMenuMeals(Long id) {
		return mealRepository.findByMenu_Id(id);
	}

	@Override
	public Set<DrinkCard> getRestaurantDrinkCard(Long id) {
		return drinkCardRepository.findByRestaurant_Id(id);
	}

	@Override
	public Set<Drink> getDrinkCardDrinks(Long id) {
		return drinkRepository.findByDrinkCard_Id(id);
	}

	@Override
	public RestaurantManager findOneById(long id) {

		return restaurantManagerRepository.findById(id);
	}

	@Override
	public Person update(String name, String surname, Long id) {
		personRepository.updateForTypeRestaurantManager(name, surname, id);
		return personRepository.findOne(id);
	}

	@Override
	public Person changePassword(Long id, String password) {
		personRepository.changePasswordForTypeRestaurantManager(id, password);
		return personRepository.findOne(id);
	}

	@Override
	public void removeMenuMeal(Long id) {
		mealRepository.removeById(id);
	}

	@Override
	public void modifyMenuMeal(Meal meal) {
		mealRepository.modifyById(meal.getName(), meal.getDescription(), meal.getPrice(), meal.getId());
	}

	@Override
	public void addMenuMeal(Meal meal) {
		mealRepository.save(meal);
	}

	@Override
	public void removeDrink(Long id) {
		drinkRepository.removeById(id);
	}

	@Override
	public void modifyDrink(Drink meal) {
		drinkRepository.modifyById(meal.getName(), meal.getDescription(), meal.getPrice(), meal.getId());
	}

	@Override
	public void addDrink(Drink meal) {
		drinkRepository.save(meal);
	}

	@Override
	public DrinkCard getRestaurantDrinkCardSpecific(Long restaurant_id, Long id) {
		return drinkCardRepository.findByRestaurant_IdAndId(restaurant_id, id);
	}

	@Override
	public Menu getRestaurantMenuSpecific(Long restaurantId, Long id) {
		return menuRepository.findByRestaurant_IdAndId(restaurantId, id);
	}

	@Override
	public Table findTable(Long id) {
		return tableRepository.findById(id);
	}

	@Override
	public Set<Table> findTablesInRestaurant(Long id) {
		return tableRepository.findByRestaurantId(id);
	}

	@Override
	public Set<Table> findTablesInSegment(Long id) {
		return tableRepository.findByRestaurantSegmentId(id);
	}

	@Override
	public void removeTable(Long id) {
		tableRepository.delete(id);
	}

	@Override
	public void modifyTable(Table table) {
		tableRepository.updateTable(table.getRestaurantSegment().getId(), table.getId());
	}

	@Override
	public void addTable(Table table) {
		tableRepository.save(table);
	}

	@Override
	public void removeSegment(Long id) {
		restaurantSegmentRepository.delete(id);
	}

	@Override
	public void modifySegment(RestaurantSegment segment) {
		restaurantSegmentRepository.updateSegment(segment.getName(), segment.getTypeOf(), segment.getId());
	}

	@Override
	public void addSegment(RestaurantSegment segment) {
		restaurantSegmentRepository.save(segment);
	}

	@Override
	public Set<Waiter> getWaiters(Long id) {
		return waiterRepository.getRestaurantStaffWaiters(id);
	}

	@Override
	public Set<Bartender> getBartenders(Long id) {
		return bartenderRepository.getRestaurantStaffBartender(id);
	}

	@Override
	public Set<Cook> getCooks(Long id) {
		return cookRepository.getRestaurantStaffCooks(id);
	}

	@Override
	public void addCook(Cook cook) {
		cookRepository.save(cook);
	}

	@Override
	public void addWaiter(Waiter waiter) {
		waiterRepository.save(waiter);
	}

	@Override
	public void addBartender(Bartender bartender) {
		bartenderRepository.save(bartender);
	}

	@Override
	public Set<WorkingSchedule> getOnDuty(Date date) {
		Date end = date;
		return workingScheduleRepository.findByStartAfterAndEndBefore(date, end);
	}

	@Override
	public List<Administrator> getAdministratorsBySupreme(boolean supreme) {
		return adminRepository.getAdminsBySupreme(false);
	}

	@Override
	public List<Administrator> getAdministrators() {
		return adminRepository.findAll();
	}

	@Override
	public void modifyAdmin(Administrator admin) {
		adminRepository.modifyAdmin(admin.getName(), admin.getSurname(), admin.getPassword(), admin.getId());
	}

	@Override
	public void removeAdmin(Administrator admin) {
		adminRepository.delete(admin.getId());
	}

	@Override
	public void createAdmin(Administrator admin) {
		adminRepository.save(admin);
	}

	@Override
	public Administrator findByEmail(String email) {
		return adminRepository.findByEmail(email);
	}

	@Override
	public List<Restaurant> findAdminRestaurants() {
		return restaurantRepository.findAll();
	}

	@Override
	public void addRestaurantManager(RestaurantManager rm) {
		restaurantManagerRepository.save(rm);
	}

	@Override
	public Double getRestaurantRating(Long id) {
		if (ratingRepository.getRestaurantRating(id) != null)
			return ratingRepository.getRestaurantRating(id);
		return new Double(0);
	}

	@Override
	public Double getWaiterRating(String name, String surname, Long id) {
		if (waiterRepository.waiterRating(name, surname, id) != null)
			return waiterRepository.waiterRating(name, surname, id);
		return new Double(0);
	}

	@Override
	public Double getMealRating(String name, Long id) {
		if (ratingRepository.getMealRating(name, id) != null)
			return ratingRepository.getMealRating(name, id);
		return new Double(0);
	}

	@Override
	public Set<WorkingSchedule> getOnDutyPeriod(Date start, Date end) {
		return workingScheduleRepository.findByStartAfterAndEndBefore(start, end);
	}

	@Override
	public void removeSchedule(Long id) {
		workingScheduleRepository.delete(id);
	}

	@Override
	public void saveSchedule(WorkingSchedule ws) {
		workingScheduleRepository.save(ws);
	}
	@Override
	public void removeWorker(Long id) {
		personRepository.delete(id);
	}

	@Override
	public void removeManager(Long id) {
		restaurantManagerRepository.delete(id);
	}

	@Override
	public List<Drink> getAllDrinks() {
		return drinkRepository.findAll();
	}
	@Override
	public Set<RestaurantSegment> getRestaurantSegments(Long id) {
		// TODO Auto-generated method stub
		return restaurantSegmentRepository.findByRestaurant_Id(id);
	}
}
