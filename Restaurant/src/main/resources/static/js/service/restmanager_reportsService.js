var restmanager_reportsService = angular.module(
		'restaurantApp.restmanager_reportsService', []);

restmanager_reportsService.factory('restmanager_reportsService',
		function($http) {

			var temp = {};

			temp.whoIsLogged = function() {
				return $http.get('/account/loggedin');
			}

			temp.logout = function() {
				return $http.get('/account/logout');
			}

			temp.getRating = function() {
				return $http.get('/restaurantManager/getRating');
			}

			temp.getWaiterRating = function(a) {
				return $http.post('/restaurantManager/getWaiterRating', a)
			}

			temp.getMealRating = function(a) {
				return $http.post('/restaurantManager/getMealRating', a)
			}

			return temp;

		})
