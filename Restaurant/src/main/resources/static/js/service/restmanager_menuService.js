var restmanager_menuService = angular.module('restaurantApp.restmanager_menuService', []);

restmanager_menuService.factory('restmanager_menuService', function($http) {
	
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
		var path = "/restaurantManager/menu";
		var full = path.concat(concat);
		return $http.get(full, id);
	}
	
	temp.getMealsInfo = function(id){
		var string = "?id=";
		var concat = string.concat(id);
		var path = "/restaurantManager/menuMeals";
		var full = path.concat(concat);
		return $http.get(full, id);
	}
	
	temp.removeMeal = function(meal){
		return $http.post("/restaurantManager/removeMeal", meal);
	}
	
	temp.addMeal = function(meal){
		return $http.post("/restaurantManager/addMeal", meal);
	}
	
	temp.modifyMeal = function(meal){
		return $http.post("/restaurantManager/modifyMeal", meal);
	}
	
	return temp;
	
})
