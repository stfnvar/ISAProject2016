var loginService = angular.module('restaurantApp.loginService', []);

loginService.factory('loginService', function($http) {
	var temp={};
	
	 temp.login = function(user) {
		return $http.post('/account/login', user);
	}
	 temp.whoIsLogged = function() {
			return $http.get('/account/loggedin');
		}
	 return temp;

	
})



