var guestController = angular.module('restaurantApp.guestController',[]);

guestController.controller('guestController', function($scope, $location, guestService,$compile){

	guestService.whoIsLogged().success(function(data){
	
			if(data.obj != null){
				
				$scope.email=data.obj.email;
				
				$('#email').text(data.obj.email);
				$('#name').text(data.obj.name);
				$('#surname').text(data.obj.surname);
				$('#sifra').text(data.obj.password);
				
				
				$scope.name=data.obj.name;
				$scope.surname=data.obj.surname;
				
			
			}else{
				$location.path('/login');
			}
				
	
	});
	
	$scope.removeFriend = function(id){
		guestService.rmFriend(id);
	}
	
	guestService.getFriends().success(function(data){
		$scope.friends = data.obj;
		var millisecondsToWait = 150;
		setTimeout(function() {
			var btns = $('#friends').find('button');
			for(var i=0; i<data.obj.length; i++){
				btns[i].setAttribute('ng-click','removeFriend('+data.obj[i].id+')');
			}
		}, millisecondsToWait);
		
		
		
	
		
		//$compile($('#friends'))($scope);
	});

	
	//odjavljivanje
	$scope.OdjavaFunc = function(){
		guestService.logout().success(function(data) {
			
			if(data.ok = true)
				$location.path('/login');
		});
	}
	
	$scope.ChangeInfoFun=function(){
		var info = {};
		info.name = $('#name').text();
		info.surname = $('#surname').text();
		info.email= $('#email').text();
		info.password = $('#sifra').text();
	
		
		
		
		
		$('#name').empty();
		$('#name').append('<input ng-model="name"  type="text" class="form-control" value='+info.name+'>');
		
		$('#surname').empty();
		$('#surname').append('<input ng-model="surname" type="text" class="form-control" value="'+info.name+'"> ');
		
		$('#email').empty();
		$('#email').append('<input ng-model="email" type="text" class="form-control" value="'+info.email+'"> ');
		
		
		$('#sifra').empty();
		$('#sifra').html('<input ng-model="sifra" type="text" class="form-control" value="'+info.password+'"> ');
		
	
	
		
		$('#changeInfoBtn').remove();
		$('#btnDiv').html('<button id="confirmInfoBtn" type="button" class="btn btn-primary btn-xs" ng-click="ConfirmInfoFun()">Confirm</button>');
		$compile($('#info-form'))($scope);
		
		$scope.sifra=info.password;
		
	}
	$scope.ConfirmInfoFun = function(){
		var info = {};
		info.name=$scope.name;
		info.surname =$scope.surname;
		info.username = $scope.email;
		info.email = $scope.email;
		info.password =$scope.sifra;
		alert(info.password);
	
		
		
		guestService.updateGuest(info).success(function(data){
			$('.container').empty();
			$('.container').append('<p>'+data.message+'</p>');
			
		});
		
		
	}
	
	$scope.redirToMyProfile = function(){
		guestService.redirMyProfile();
	}

})