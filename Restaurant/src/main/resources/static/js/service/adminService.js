var adminService = angular.module('restaurantApp.adminService', []);

adminService.factory('adminService', function($http) {
	
	var temp = {};
	
	temp.whoIsLogged = function() {
		return $http.get('/account/loggedin');
	}
	
	temp.logout = function(){
		return $http.get('/account/logout');
	}
	
	return temp;
	
})
