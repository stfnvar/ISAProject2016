var cookService = angular.module('restaurantApp.cookService', []);

cookService.factory('cookService', function($http) {
	
	var temp = {};
	
	temp.whoIsLogged = function() {
		return $http.get('/account/loggedin');
	}
	
	temp.logout = function(){
		return $http.get('/account/logout');
	}
	
	temp.checkFirstTime = function(id){
		return $http.get('/cook/checkFirstTime/'+id);
	}
	
	temp.ftChangePW = function(id, pw){
		return $http.post('/cook/ftChangePW/'+id+"/"+pw);
	}
	
	temp.updateCook = function(cook){
		return $http.post('/cook/updateCook', cook);
	}
	
	temp.getAllOrderedMeals = function() {
		return $http.get('/orderedMeals/getAll');
	}
	
	temp.acceptMeal = function(id){
		return $http.post('/orderedMeals/acceptMeal/'+id);
	}
	
	temp.makeMeal = function(id){
		return $http.post('/orderedMeals/makeMeal/' + id);
	}
	
	temp.getOnDutyDay = function(date){
		return $http.post('/cook/getOnDutyDay', date);
	}
	
	return temp;
	
})
