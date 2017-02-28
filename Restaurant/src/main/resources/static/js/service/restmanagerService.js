var restmanagerService = angular.module('restaurantApp.restmanagerService', []);

restmanagerService.factory('restmanagerService', function($http) {
	
	var temp = {};
	
	temp.whoIsLogged = function() {
		return $http.get('/account/loggedin');
	}

	temp.logout = function(){
		return $http.get('/account/logout');
	}
	
	temp.getMenus = function() {
		var id = null;
		return $http.get("/restaurantManager/menu", id);
	}
	
	temp.getDrinkCards = function() {
		var id = null;
		return $http.get('/restaurantManager/drinkCard', id);
	}
	
	temp.applyChangesToProfile = function(restmanager){
		return $http.post('/restaurantManager/applyProfileChanges', restmanager);
	}
	
	temp.changePassword = function(data){
		return $http.post('/restaurantManager/changePassword', data);
	}
	
	return temp;
})


