var loginController = angular.module('restaurantApp.loginController', []);

loginController.controller('loginController', function($scope, loginService){
	
	$scope.login = function(){
		var user = {
			email : $scope.email,
			password : $scope.password
		}
		
		var str = JSON.stringify(user);
		
		loginService.login(str).success(function(data) {
			if(data != ""){
				if(data.tipKorisnika == 'GOST')
					$location.path('/index');
			}
		});
	}
})

