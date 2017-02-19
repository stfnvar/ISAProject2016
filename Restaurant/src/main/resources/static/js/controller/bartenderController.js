var bartenderController = angular.module('restaurantApp.bartenderController',[]);

guestController.controller('bartenderController', function($scope, $location, bartenderService){
	
	bartenderService.whoIsLogged().success(function(data){
		
		if(data.obj != null)
				$scope.email=data.obj.email;
		else
			$location.path('/login');

});
	
	//odjavljivanje
	$scope.OdjavaFunc = function(){
		bartenderService.logout().success(function(data) {
			
			if(data.ok = true)
				$location.path('/login');
		});
	}
	
	
})