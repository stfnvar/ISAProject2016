var guestService = angular.module('restaurantApp.guestService', []);

guestService.factory('guestService', function($http,$location) {
	
	var temp = {};
	
	temp.whoIsLogged = function() {
		return $http.get('/account/loggedin');
	}
	
	temp.logout = function(){
		return $http.get('/account/logout');
	}
	temp.updateGuest = function(guest){
		return $http.post('/account/updateGuest', guest);
	}
	
	temp.redirMyProfile = function(){
		$location.path('/homepage');
	}
	temp.getFriends = function(){
		return $http.get('/account/test');
	}
	temp.rmFriend = function(id){
		alert('TODO remove function in rest');
		console.log(id);
	}
	
	
	
	return temp;
	
})

