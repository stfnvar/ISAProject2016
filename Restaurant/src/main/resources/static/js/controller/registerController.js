var registerController = angular.module('restaurantApp.registerController', []);

registerController.controller('registerController', function($scope, registerService) {
	
	


	$scope.register = function(){
		
		var pass1 = $scope.password;
	  	var pass2 = $scope.cpassword;
	  	var n = pass1.localeCompare(pass2);
	  	
	  		if(n!=0){
	  			$scope.message = "Password error...";
	  			return;
	  		}
		
		var guest = {
			username : $scope.email,
			name : $scope.name,
			surname : $scope.surname,
			email : $scope.email,
			password : $scope.password,
			active : 0
		}
		
		var str = JSON.stringify(guest);
		
		registerService.register(str).success(function(data) {
			//alert(data.ok);
    		if(data.ok == true){
    			$('#register-form').remove();
    			$scope.message = "Please check your e-mail for activation...";
    			
    			
    		}else {
    			 $scope.name='';
				 $scope.surname='';
				 $scope.email='';
				 $scope.password='';
				 $scope.cpassword='';
				 $scope.email='';
				 
				 $scope.errpass = 'Already registered user with this e-mail...';
    		}
    		
		});
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
})