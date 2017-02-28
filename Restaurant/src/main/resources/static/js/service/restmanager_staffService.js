var restmanager_staffService = angular.module('restaurantApp.restmanager_staffService', []);

restmanager_staffService.factory('restmanager_staffService', function($http) {
	
	var temp = {};
	
	temp.whoIsLogged = function() {
		return $http.get('/account/loggedin');
	}
	
	temp.logout = function(){
		return $http.get('/account/logout');
	}
	
	temp.getWaiters = function(){
		return $http.get('/restaurantManager/getWaiters')
	}
	
	temp.getBartenders = function(){
		return $http.get('/restaurantManager/getBartenders')
	}
	
	temp.getCooks = function(){
		return $http.get('/restaurantManager/getCooks')
	}
	
	temp.addCook = function(c){
		return $http.post('/restaurantManager/addCook', c)
	}
	
	temp.addWaiter = function(c){
		return $http.post('/restaurantManager/addWaiter', c)
	}
	
	temp.addBartender = function(c){
		return $http.post('/restaurantManager/addBartender', c)
	}
	
	temp.getOnDutyDay = function(date){
		return $http.post('/restaurantManager/getOnDutyDay', date);
	}
	
	temp.getOnDuty = function(date){
		return $http.post('/restaurantManager/getOnDuty', date);
	}
	
	temp.removeSchedule = function(id){
		return $http.post('/restaurantManager/removeSchedule', id);
	}
	
	temp.getWorkers = function(){
		return $http.get('/restaurantManager/getWorkers');
	}
	
	temp.addSchedule = function(ws){
		return $http.post('/restaurantManager/addSchedule', ws);
	}
	
	return temp;
	
})
