var cookService = angular.module('restaurantApp.cookService', []);

cookService.factory('cookService', function($http) {
	
	var temp = {};
	
	temp.whoIsLogged = function() {
		return $http.get('/account/loggedin');
	}
	
	temp.logout = function(){
		return $http.get('/account/logout');
	}
	
	return temp;
	
})
