var offererService = angular.module('restaurantApp.offererService', []);

offererService.factory('offererService', function($http) {
	
	var temp = {};
	
	temp.whoIsLogged = function() {
		return $http.get('/account/loggedin');
	}
	
	temp.logout = function(){
		return $http.get('/account/logout');
	}
	
	temp.applyChangesToProfile = function(restmanager){
		return $http.post('/offerer/applyProfileChanges', restmanager);
	}
	
	temp.changePassword = function(data){
		return $http.post('/offerer/changePassword', data);
	}
	
	temp.changeFirstPassword = function(data){
		return $http.post('/offerer/changeFirstPass', data);
	}
	
	temp.getOffers = function(){
		return $http.get('/offerer/getOffersForOfferer');
	}
	
	temp.getAnnoun = function(){
		return $http.get('/offerer/getAnnoun');
	}
	
	temp.addOffer = function(off){
		return $http.post('/offerer/addOffer', off);
	}
	
	return temp;
})
