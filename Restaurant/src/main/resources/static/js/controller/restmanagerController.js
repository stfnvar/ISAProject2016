var restmanagerController = angular.module('restaurantApp.restmanagerController',[]);

restmanagerController.controller('restmanagerController', function($scope, $location, restmanagerService){
	
	restmanagerService.whoIsLogged().success(function(data){
		
		if(data.obj != null)
				$scope.email=data.obj.email;
		else
			$location.path('/login');

});
	
	//odjavljivanje
	$scope.OdjavaFunc = function(){
		restmanagerService.logout().success(function(data) {
			
			if(data.ok = true)
				$location.path('/login');
		});
	}
	
	
})