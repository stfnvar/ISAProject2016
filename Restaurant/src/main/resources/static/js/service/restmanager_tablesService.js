var restmanager_tablesService = angular.module('restaurantApp.restmanager_tablesService', []);

restmanager_tablesService.factory('restmanager_tablesService', function($http) {
	
	var temp = {};
	
	temp.whoIsLogged = function() {
		return $http.get('/account/loggedin');
	}
	temp.logout = function(){
		return $http.get('/account/logout');
	}
	
	temp.getTables = function(){
		return $http.get('/restaurantManager/tables');
	}
	
	temp.getRestaurantSegments = function(){
		return $http.get('/restaurantManager/restaurantSegments');
	}
	
	temp.getSegmentTables = function(id){
		return $http.post('/restaurantManager/segmentTables', id);
	}
	
	temp.removeTable = function(param){
		return $http.post('/restaurantManager/removeTable', param);
	}
	
	temp.removeSegment = function(param){
		return $http.post('/restaurantManager/removeSegment', param);
	}
	
	temp.modifyTable = function(param){
		return $http.post('/restaurantManager/modifyTable', param);
	}
	
	temp.modifySegment= function(param){
		return $http.post('/restaurantManager/modifySegment', param);
	}
	
	temp.addTable = function(param){
		return $http.post('/restaurantManager/addTable', param);
	}
	
	temp.addSegment = function(param){
		return $http.post('/restaurantManager/addSegment', param);
	}
	
	return temp;
	
})

