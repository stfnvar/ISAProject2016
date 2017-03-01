var borderController = angular.module('restaurantApp.borderController',[]);

borderController.controller('borderController', function($scope, $location, $window, $compile, bartenderService){
	
	$scope.orderedDrinks = {};
	
	bartenderService.getAllOrderedDrinks().success(function(data){
		for(var i = 0; i < data.length; i++){
			if(data[i].ready == 0)
				data[i].ready = 'Not ready';
			else
				data[i].ready = 'Ready';
		}
		$scope.orderedDrinks = data;
	});
	
	$scope.signalize = function(x){
		alert(x.id);
		bartenderService.drinkIsReady(x.id).success(function(data){
			alert("uspesno!");
			$window.location.reload();
		});
	}
	
})