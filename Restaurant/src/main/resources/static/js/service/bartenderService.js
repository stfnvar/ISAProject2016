var bartenderService = angular.module('restaurantApp.bartenderService', []);

bartenderService.factory('bartenderService', function($http) {
	
	var temp = {};
	
	temp.whoIsLogged = function() {
		return $http.get('/account/loggedin');
	}
	
	temp.logout = function(){
		return $http.get('/account/logout');
	}
	
	return temp;
	
})
