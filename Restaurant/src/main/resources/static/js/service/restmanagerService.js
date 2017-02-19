var restmanagerService = angular.module('restaurantApp.restmanagerService', []);

restmanagerService.factory('restmanagerService', function($http) {
	
	var temp = {};
	
	temp.whoIsLogged = function() {
		return $http.get('/account/loggedin');
	}
	
	temp.logout = function(){
		return $http.get('/account/logout');
	}
	
	return temp;
	
})
