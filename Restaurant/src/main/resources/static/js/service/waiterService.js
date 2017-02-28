var waiterService = angular.module('restaurantApp.waiterService', []);

waiterService.factory('waiterService', function($http) {
	
	var temp = {};
	
	temp.whoIsLogged = function() {
		return $http.get('/account/loggedin');
	}
	
	temp.logout = function(){
		return $http.get('/account/logout');
	}
	
	temp.checkFirstTime = function(id){
		return $http.get('/waiter/checkFirstTime/'+id);
	}
	
	temp.ftChangePW = function(id, pw){
		return $http.post('/waiter/ftChangePW/'+id+"/"+pw);
	}
	
	temp.updateWaiter = function(waiter){
		return $http.post('/waiter/updateWaiter', waiter);
	}
	
	temp.getOnDutyDay = function(date){
		return $http.post('/waiter/getOnDutyDay', date);
	}
	
	return temp;
	
})
