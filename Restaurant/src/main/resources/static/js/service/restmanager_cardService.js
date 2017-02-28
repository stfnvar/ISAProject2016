var restmanager_cardService = angular.module('restaurantApp.restmanager_cardService', []);

restmanager_cardService.factory('restmanager_cardService', function($http) {
	
	var temp = {};
	
	temp.whoIsLogged = function() {
		return $http.get('/account/loggedin');
	}
	
	temp.logout = function(){
		return $http.get('/account/logout');
	}
	
	temp.getMenuInfo = function(id){
		var string = "?id=";
		var concat = string.concat(id);
		var path = "/restaurantManager/drinkCard";
		var full = path.concat(concat);
		return $http.get(full, id);
	}
	
	temp.getMealsInfo = function(id){
		var string = "?id=";
		var concat = string.concat(id);
		var path = "/restaurantManager/drinks";
		var full = path.concat(concat);
		return $http.get(full, id);
	}
	
	temp.removeMeal = function(meal){
		return $http.post("/restaurantManager/removeDrink", meal);
	}
	
	temp.addMeal = function(meal){
		return $http.post("/restaurantManager/addDrink", meal);
	}
	
	temp.modifyMeal = function(meal){
		return $http.post("/restaurantManager/modifyDrink", meal);
	}
	
	return temp;
	
})
