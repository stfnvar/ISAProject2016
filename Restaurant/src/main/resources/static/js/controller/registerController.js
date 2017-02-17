var registerController = angular.module('restaurantApp.registerController', []);

registerController.controller('registerController', function($scope, registerService) {

	$scope.register = function(){
		var user = {
			username : $scope.username,
			name : $scope.name,
			surname : $scope.surname,
			email : $scope.email,
			password : $scope.password
		}
		
		var str = JSON.stringify(user);
		
		registerService.register(str);
	}
})