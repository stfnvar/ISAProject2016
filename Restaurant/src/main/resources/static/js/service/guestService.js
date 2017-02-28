var guestService = angular.module('restaurantApp.guestService', []);

guestService.factory('guestService', function($http) {
	
	var temp = {};
	
	temp.whoIsLogged = function() {
		return $http.get('/account/loggedin');
	}
	
	temp.logout = function(){
		return $http.get('/account/logout');
	}
	
	return temp;
	
})

