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
	
	temp.addOfferer = function(data){
		return $http.post('/restaurantManager/registerOfferer', data);
	}
	
	temp.getOfferers = function(data){
		return $http.get('/restaurantManager/getOfferers');
	}
	
	temp.addGrocery = function(data){
		return $http.post('/restaurantManager/addGrocery', data);
	}
	
	temp.addAnnoun = function(data){
		return $http.post('/restaurantManager/addAnnoun', data);
	}
	
	temp.getGroceries = function(){
		return $http.get('/restaurantManager/getGroceries');
	}
	
	temp.getAnnoun = function(){
		return $http.get('/restaurantManager/getMyAnnoun');
	}
	
	temp.getOffers = function(){
		return $http.get('/restaurantManager/getOffers');
	}
	
	temp.addWantedItems = function(jsonwi, data){
		return $http.post('/restaurantManager/addWantedItems/' + data, jsonwi)
	}
	
	temp.getWantedItemsForAnn = function(ae){
		return $http.post('/restaurantManager/getWantedItemsForAnn', ae);
	}
	
	temp.acceptOffer = function(ae){
		return $http.post('/restaurantManager/acceptOffer', ae);
	}
	
	
	temp.getOffersForA = function(id){
		return $http.post('/restaurantManager/getOffersForAnnoun', id);
	}
	
	return temp;
})


