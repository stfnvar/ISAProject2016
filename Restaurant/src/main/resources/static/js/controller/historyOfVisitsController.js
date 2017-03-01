var historyOfVisitsController = angular.module('restaurantApp.historyOfVisitsController', []);

historyOfVisitsController.controller('historyOfVisitsController', function($scope, $location, orderService, restmanager_reportsService){

	orderService.getAllRestaurants().success(function(data){
		if(data.ok==true ){
			$scope.restaurants = data.obj;		
		}else
		   $location.path('/error');
	});
	
	$scope.restaurant = {};
	$scope.service = {};
	$scope.meals = {};

	var restaurantModal;
	
	$scope.restaurantRatings = function(x){
		restaurantModal = x;
		$scope.restaurant.id = x.id;
		$scope.restaurant.name = x.name;
		orderService.getRestaurantRating(x).success(function(data){
			$scope.restaurant.rating = data;
		});
		orderService.getWaiterRating(x).success(function(data){
			if(data==""){
				$scope.service.rating = "";
				return;
			}
			$scope.service.rating = data;
		});
		orderService.getMealRating(x).success(function(pera){
			if(pera==""){
				$scope.meals.rating = "";
				return;
			}
			$scope.meals.rating = pera;
		});
		
		$('#ratingsModal').modal('show');
	}
	
	$scope.giveRatingClick = function(rating){
		alert(restaurantModal.id);
		alert(rating);
		orderService.giveRatingToRestaurant(restaurantModal.id, rating).success(function(data){
			//alert("hooj");
			$scope.restaurant.rating = data;
		});
	}
	
	$scope.giveRatingMealsClick = function(rating){
		//alert(rating);
		orderService.giveRatingToMeal(restaurantModal.id, rating).success(function(data){
			if(data == ""){
				$scope.meals.rating = "";
				return;
			}
			$scope.meals.rating = data;
		});
	}
	
	
	$scope.giveRatingServiceClick = function(rating){
		orderService.giveRatingToWaiter(restaurantModal.id, rating).success(function(data){
			if(data == ""){
				$scope.service.rating = "";
				return;
			}
			$scope.service.rating = data;
		});
	}
	
})