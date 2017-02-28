package com.restaurant.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

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
import com.restaurant.model.Worker;
import com.restaurant.model.WorkingSchedule;
import com.restaurant.model.RestaurantSegment;
import com.restaurant.model.Table;
import com.restaurant.model.Waiter;

public interface RestaurantManagerService {

	Restaurant getRestaurantProfile(Long id);

	Set<RestaurantSegment> getRestaurantSegments(Long id);

	List<Worker> getWorkers(Long id);

	Set<Menu> getRestaurantMenu(Long id);

	Set<Meal> getMenuMeals(Long id);

	Menu getRestaurantMenuSpecific(Long restaurant_id, Long id);

	Set<DrinkCard> getRestaurantDrinkCard(Long id);

	DrinkCard getRestaurantDrinkCardSpecific(Long restaurant_id, Long id);

	Set<Drink> getDrinkCardDrinks(Long id);

	RestaurantManager findOneById(long id);

	Person update(String name, String surname, Long id);

	Person changePassword(Long id, String password);

	void removeMenuMeal(Long id);

	void modifyMenuMeal(Meal meal);

	void addMenuMeal(Meal meal);

	void removeDrink(Long id);

	void modifyDrink(Drink meal);

	void addDrink(Drink meal);

	Table findTable(Long id);

	Set<Table> findTablesInRestaurant(Long id);

	Set<Table> findTablesInSegment(Long id);

	void removeTable(Long id);

	void modifyTable(Table table);

	void addTable(Table table);

	void removeSegment(Long id);

	void modifySegment(RestaurantSegment segment);

	void addSegment(RestaurantSegment segment);

	Set<Waiter> getWaiters(Long id);

	Set<Bartender> getBartenders(Long id);

	Set<Cook> getCooks(Long id);

	void addCook(Cook cook);

	void addWaiter(Waiter waiter);

	void addBartender(Bartender bartender);
	
    Set<WorkingSchedule> getOnDuty(Date date);
    
    Set<WorkingSchedule> getOnDutyPeriod(Date start, Date end);
    
    List<Administrator> getAdministratorsBySupreme(boolean supreme);
    
    List<Administrator> getAdministrators();
    
    void modifyAdmin(Administrator admin);
    
    void removeAdmin(Administrator admin);
    
    void createAdmin(Administrator admin);
    
    Administrator findByEmail(String email);
    
    List<Restaurant> findAdminRestaurants();
    
    void addRestaurantManager(RestaurantManager rm);
    
    Double getRestaurantRating(Long id);
    
    Double getWaiterRating(String name, String surname, Long id);
    
    Double getMealRating(String name, Long id);
    
    void removeSchedule(Long id);
    
    void saveSchedule(WorkingSchedule ws);
}
