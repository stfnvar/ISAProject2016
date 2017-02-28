var loginController = angular.module('restaurantApp.loginController', []);

loginController.controller('loginController', function($scope, $location, loginService){

	loginService.whoIsLogged().success(function(data){
		
		if(data.obj != null)
			$location.path('/homepage');
		
			

});
	
	
	$scope.login = function(){
		var user = {
			email : $scope.email,
			password : $scope.password
		}
		
		var str = JSON.stringify(user);
		
		loginService.login(str).success(function(data) {
			if(data.ok = false && data.obj !=null){
	    		
				$scope.errlog = 'Please activate your account first...';
    			
    		}else if(data.obj == null){	
    			$scope.errlog  = 'Wrong combination! Try again...';
    			$scope.email='';
    			$scope.password='';
    			
    			
    			  
    		}else{
    			
    			if(data.message=='gost'){
    				$location.path('/homepage');
    			}else if(data.message=='rmanager'){
    				$location.path('/restmanager');
    			}else if(data.message=='admin'){
    				$location.path('/admin');
    			}else if(data.message=='radnik'){
    				if(data.obj.type == 'BARTENDER'){
    					$location.path('/bartender');
    				}else if(data.obj.type == 'COOK'){
    					$location.path('/cook');
    				}else if(data.obj.type == 'WAITER'){
    					$location.path('/waiter');
    				}
    			}
    			
    		}
		});
	}

})

