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
		return $http.get('/account/rmfriend/'+id)
		
	}
	temp.getEnemies = function(){
		return $http.get('/account/notfriends/');
	}
	temp.search = function(op, name, surname){
		return $http.get('/account/search/'+op+'/'+name+'/'+surname);
	}
	temp.addfriend = function(id){
		
		return $http.post('/account/addfriend/'+id);
	}
	temp.searchFriends = function(op, name, surname){
		return $http.get('/account/searchFriends/'+op+'/'+name+'/'+surname);
	}
	
	
	
	return temp;
	
})

