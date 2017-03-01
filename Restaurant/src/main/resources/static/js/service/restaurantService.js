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
	
	temp.getFriends = function(){
		return $http.get('/account/test');
	}
	
	temp.reserveTables = function(start, end, arrt, restid){
		return $http.post('/restaurant/reserveTables/'+start+'/'+end+'/'+arrt+'/'+restid);
	}
	
	temp.invite = function(start, end, friend, rest){
		return $http.post('/restaurant/invite/'+start+'/'+end+'/'+friend+'/'+rest);
	}
	temp.myinv = function(){
		return $http.get('/restaurant/myinvitations/');
	}
	
	temp.acceptInv = function(id){
		return $http.post('/restaurant/acceptinv/'+id);
	}
	
	temp.cancelInv = function(id){
		return $http.post('/restaurant/cancelinv/'+id);
	}
	
	temp.getTablesForReservation = function(start, end, id){
		return $http.get('/restaurant/getOrderTables/'+start+'/'+end+'/'+id);
	}

	
	
	temp.getAvailableDesks = function(s, e, id){
		return $http.get('/restaurant/getAvailableDesks/'+s+'/'+e+'/'+id);
	}
	
	
	return temp;
})