var orderService = angular.module('restaurantApp.orderService', []);

orderService.factory('orderService', function($http) {

	var temp = {};
	
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
	
	
	return temp;
})