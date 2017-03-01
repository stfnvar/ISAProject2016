var restaurantService = angular.module('restaurantApp.restaurantService', []);


restaurantService.factory('restaurantService', function($http) {
	
	var temp={};
	
	temp.whoIsLogged = function() {
		
		return $http.get('/account/loggedin');
	}
	
	temp.getAllRestaurants = function(){
		return $http.get('/restaurant/getRestaurants');
	}
	
	temp.getAllDesks = function(id){
		return $http.get('/restaurant/getAllDesks/'+id);
	}
	
	
	temp.getAvailableDesks = function(s, e, id){
		return $http.get('/restaurant/getAvailableDesks/'+s+'/'+e+'/'+id);
	}
	
	
	return temp;
})