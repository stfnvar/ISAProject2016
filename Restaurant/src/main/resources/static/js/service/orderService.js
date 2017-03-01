var orderService = angular.module('restaurantApp.orderService', []);

orderService.factory('orderService', function($http) {

	var temp = {};
	
	temp.getMealRating = function(restaurant){
		return $http.post('/waiter/getMealRating', restaurant)
	}
	
	temp.giveRatingToMeal = function(id, rating){
		return $http.post('/waiter/giveRatingToMeal/'+id+"/"+rating);
	}
	
	temp.getWaiterRating = function(restaurant){
		return $http.post('/waiter/getWaiterRating', restaurant)
	}
	
	temp.giveRatingToWaiter = function(id, rating){
		return $http.post('/waiter/giveRatingToWaiter/'+id+"/"+rating);
	}
	
	temp.getRestaurantRating = function(restaurant){
		return $http.post('/waiter/getRestaurantRating', restaurant)
	}
	
	temp.getAllRestaurants = function(){
		return $http.get('/restaurant/getRestaurants');
	}
	
	temp.giveRatingToRestaurant = function(id, rating){
		return $http.post('/waiter/giveRatingToRestaurant/'+id+"/"+rating);
	}
	
	temp.findAllTables = function(){
		return $http.get('/tables/getAll');
	}
	
	temp.addOrder = function(drinkMeal){
		return $http.post('/waiter/addOrder', drinkMeal);
	}
	
	temp.findAllOrders = function(){
		return $http.get('/waiter/getAllOrders');
	}
	
	temp.createBill = function(order){
		return $http.post('/waiter/createBill', order);
	}
	
	temp.getDrinksByOrder = function(id){
		return $http.post('/waiter/getDetails', id);
	}
	temp.getMealsByOrder = function(id){
		return $http.post('/waiter/getDetailsMeals', id);
	}
	
	temp.lili = function(li){
		return $http.post('/waiter/lili', li);
	}
	
	temp.editOrder = function(drinkMeal){
		return $http.post('/waiter/editOrder', drinkMeal);
	}
	
	temp.editOrderDR = function(ordiDrinks){
		return $http.post('/waiter/editOrderDrinks', ordiDrinks);
	}
	
	temp.editOrderMEALS = function(ordiMeals){
		return $http.post('/waiter/editOrderMeals', ordiMeals);
	}
	
	temp.findara = function(){
		return $http.post('/tables/getAllAndWaitersTables');
	}
	
	temp.getDrinksRestaurant = function(){
		return $http.get('/orderedDrinks/getDrinksRestaurant');
	}
	
	temp.getMealsRestaurant = function(){
		return $http.get('/orderedMeals/getMealsRestaurant');
	}
	
	temp.addMea = function(m){
		return $http.post('/orderedMeals/addToOrder', m);
	}
	
	temp.addDri = function(m){
		return $http.post('/orderedDrinks/addToOrder', m);
	}
	
	temp.checkAcceptedMeal = function(order){
		return $http.post('/orderedMeals/checkAcceptedMeal', order);
	}
	
	return temp;
})