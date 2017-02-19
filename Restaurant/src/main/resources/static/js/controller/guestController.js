var guestController = angular.module('restaurantApp.guestController',[]);

guestController.controller('guestController', function($scope, $location, guestService){

	guestService.whoIsLogged().success(function(data){
	
			if(data.obj != null)
					$scope.email=data.obj.email;
			else
				$location.path('/login');
	
	});

	
	//odjavljivanje
	$scope.OdjavaFunc = function(){
		guestService.logout().success(function(data) {
			
			if(data.ok = true)
				$location.path('/login');
		});
	}

})