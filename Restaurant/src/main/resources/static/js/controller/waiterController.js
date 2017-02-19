var waiterController = angular.module('restaurantApp.waiterController',[]);

waiterController.controller('waiterController', function($scope, $location, waiterService){
	
	waiterService.whoIsLogged().success(function(data){
		
		if(data.obj != null)
				$scope.email=data.obj.email;
		else
			$location.path('/login');

});
	
	//odjavljivanje
	$scope.OdjavaFunc = function(){
		waiterService.logout().success(function(data) {
			
			if(data.ok = true)
				$location.path('/login');
		});
	}
	
	
})