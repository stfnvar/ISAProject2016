var registerService = angular.module('restaurantApp.registerService', []);

registerService.factory('registerService', function($http) {
	
	var temp = {};
	
	temp.register = function(user) {
		return $http.post('/account/register', user);
	}
	
	return temp;
	
})