var adminController = angular.module('restaurantApp.adminController',[]);

adminController.controller('adminController', function($scope, $location, adminService){
	
	adminService.whoIsLogged().success(function(data){
		
		if(data.obj != null)
				$scope.email=data.obj.email;
		else
			$location.path('/login');

});
	
	//odjavljivanje
	$scope.OdjavaFunc = function(){
		adminService.logout().success(function(data) {
			
			if(data.ok = true)
				$location.path('/login');
		});
	}
	
	
})