var bartenderService = angular.module('restaurantApp.bartenderService', []);

bartenderService.factory('bartenderService', function($http) {
	
	var temp = {};
	
	temp.whoIsLogged = function() {
		return $http.get('/account/loggedin');
	}
	
	temp.logout = function(){
		return $http.get('/account/logout');
	}
	
	temp.checkFirstTime = function(id){
		return $http.get('/bartender/checkFirstTime/'+id);
	}
	
	temp.ftChangePW = function(id, pw){
		return $http.post('/bartender/ftChangePW/'+id+"/"+pw);
	}
	
	temp.updateBart = function(bart){
		return $http.post('/bartender/updateBartender', bart);
	}
	
	temp.getAllOrderedDrinks = function() {
		return $http.get('/orderedDrinks/getAll');
	}
	
	temp.drinkIsReady = function(id){
		return $http.post('/orderedDrinks/updateReady/'+id);
	}
	
	temp.getOnDutyDay = function(date){
		return $http.post('/bartender/getOnDutyDay', date);
	}
	
	return temp;
	
})
