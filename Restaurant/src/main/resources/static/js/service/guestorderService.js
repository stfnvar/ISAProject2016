var guestorderService = angular.module('restaurantApp.guestorderService', []);

guestorderService.factory('guestorderService', function($http) {
	
	var temp = {};
	
	temp.addOrder = function(drinkMeal){
		
		return $http.post('/restaurant/addOrder', drinkMeal);
	}
	
	
	temp.getStoloviRezervacije = function(){
		return $http.get('/restaurant/getTablesOfReservation');
	}
	
	return temp;
	
	
})
