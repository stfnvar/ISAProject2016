var cookController = angular.module('restaurantApp.cookController',[]);

cookController.controller('cookController', function($scope, $location, cookService){
	
	cookService.whoIsLogged().success(function(data){
		
		if(data.obj != null)
				$scope.email=data.obj.email;
		else
			$location.path('/login');

});
	
	//odjavljivanje
	$scope.OdjavaFunc = function(){
		cookService.logout().success(function(data) {
			
			if(data.ok = true)
				$location.path('/login');
		});
	}
	
	
})