var cordersController = angular.module('restaurantApp.cordersController',[]);

cordersController.controller('cordersController', function($scope, $location, $window, $compile, cookService){
	
	$scope.orderedMeals = {};
	//$scope.orderedMeals.showText = false;
	//$scope.orderedMeals.showButton = true;
	
	cookService.getAllOrderedMeals().success(function(data){
		for(var i = 0; i < data.length; i++){
			if(data[i].acceptedMeal == 0)
				data[i].acceptedMeal = 'Not accepted';
			else{
				data[i].acceptedMeal = 'Accepted';
				if(data[i].ready == 1){
					data[i].acceptedMeal = "Ready";
				}
			}
		}
		$scope.orderedMeals = data;
	});
	
	$scope.signalizeAccept = function(x, $event){
		//$scope.x.showText = true;
		//$scope.x.showButton = false;
		//alert(x.acceptedMeal);
		//$($event.toElement).hide();
		
		alert(x.id);
		cookService.acceptMeal(x.id).success(function(data){
			alert("uspesno!");
			$window.location.reload();
		});
	}
	
	$scope.signalizeReady = function(x){
		if(x.acceptedMeal == "Not accepted"){
			alert("Meal is not accepted yet.")
			return;
		}
		cookService.makeMeal(x.id).success(function(data){
			$window.location.reload();
		});
	}
	
	
})