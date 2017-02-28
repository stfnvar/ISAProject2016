var adminService = angular.module('restaurantApp.adminService', []);

adminService.factory('adminService', function($http) {
	
	var temp = {};
	
	temp.whoIsLogged = function() {
		return $http.get('/account/loggedin');
	}
	
	temp.logout = function(){
		return $http.get('/account/logout');
	}
	
	temp.getAdmins = function(){
		return $http.get('/restaurantManager/getAdmins')
	}
	
	temp.removeAdmin = function(a){
		return $http.post('/restaurantManager/removeAdmin', a)
	}
	
	temp.modifyAdmin = function(a){
		return $http.post('/restaurantManager/modifyAdmin', a)
	}
	
	temp.addAdmin = function(a){
		return $http.post('/restaurantManager/addAdmin', a)
	}
	
	temp.getRestaurants = function(a){
		return $http.get('/restaurantManager/getAdminRestaurants', a)
	}
	
	temp.removeRestaurant = function(a){
		return $http.post('/restaurantManager/removeRestaurant', a)
	}
	
	temp.addRestaurant = function(a){
		return $http.post('/restaurantManager/addRestaurant', a)
	}
	
	temp.addManager = function(a){
		return $http.post('/restaurantManager/addManager', a)
	}
	
	temp.getRestaurantManagers = function(restaurant){
		return $http.post('/restaurantManager/getRestaurantManagersAdmin', restaurant)
	}
	
	temp.removeRestaurantManager = function(r){
		return $http.post('/restaurantManager/removeManager', r);
	}
	
	return temp;
	
})
