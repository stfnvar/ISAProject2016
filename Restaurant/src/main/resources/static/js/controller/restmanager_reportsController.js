var restmanager_reportsController = angular.module(
		'restaurantApp.restmanager_reportsController', []);

restmanager_reportsController.controller('restmanager_reportsController',
		function($scope, $location, restmanager_reportsService) {
			
			$scope.waiter = {};
			$scope.meal = {};
		
			restmanager_reportsService.whoIsLogged().success(function(data) {

				if (data.obj != null){
					$scope.email = data.obj.email;
					$scope.restaurant = data.obj.restaurant;
					restmanager_reportsService.getRating().success(function(data){
						$scope.restaurantRating = data;
					});
				}else
					$location.path('/login');

			});

			$scope.OdjavaFunc = function() {
				restmanager_reportsService.logout().success(function(data) {

					if (data.ok = true)
						$location.path('/login');
				});
			}
			$scope.refreshRating = function(){
				restmanager_reportsService.getRating().success(function(data){
					$scope.restaurantRating = data;
				});
			}
			
			$scope.waiterRating = function(w){
				var v = {
					name : w.name,
					surname : w.surname
				}
				json = JSON.stringify(v);
				restmanager_reportsService.getWaiterRating(json).success(function(data){
					$scope.waiterRatingData = data;
				});
			}
			
			$scope.mealRating = function(w){
				var v = {
					name : w.name,
				}
				json = JSON.stringify(w);
				restmanager_reportsService.getMealRating(json).success(function(data){
					$scope.mealRatingData = data;
				});
			}
		})