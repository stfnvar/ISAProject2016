var guestController = angular.module('restaurantApp.guestController',[]);

guestController.controller('guestController', function($scope, $location,$window, guestService,$compile){

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
	
	$scope.FriendsMnu = function(){
		$location.path('/friends');
	}
	
	$scope.removeFriend = function(id){
		guestService.rmFriend(id).success(function(data){
			var btn = $('button[data-bid='+id+']');
			btn.parent().parent().remove();
		});

	}
	$scope.addFriend = function(id){
		guestService.addfriend(id).success(function(){
			 $window.location.reload();
		});
	}
	
	guestService.getFriends().success(function(data){
		if(data.obj!=null){
			$scope.friends = data.obj;
		}
		
		
		$compile($('#friends'))($scope);
		
		
	});
	/*
	guestService.getEnemies().success(function(data){
		$scope.enemies = data.obj;
		
		$compile($('#enemies'))($scope);
		
		
	});
*/
	
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
			/*$('.container').empty();
			$('.container').append('<p>'+data.message+'</p>');
			*/
			 $window.location.reload();
		});
		
		
	}
	
	$scope.searchPeople = function(){
		var op = $('#searchSwitch').val();
		var name = $('#searchName').val();
		var surname = $('#searchSurname').val();
		
		
		if(op == 'or'){
			if(name != '' && surname != ''){
				$('#err').text("Only one field has to be filled for OR search...");
			}else if(name=='' && surname==''){
				$('#err').text("Just one field has to be filled for OR search...");
			}else {
				if(name=='')name='!x!';
				if(surname=='')surname='!x!';
				guestService.search(op,name,surname).success(function(data){
					/*$('#enemiesBody').children().remove();
					 var repeat =' <tr ng-repeat="y in enemies"><td>{{y.name}}</td>';
						 repeat+='<td>{{y.surname}}</td>';
					 	 repeat+='<td><button data-baid="{{y.id}}" type="button" class="btn btn-primary btn-xs" ng-click="addFriend(y.id)">add</button></td> </tr>';
					 	$('#enemiesBody').append(repeat);
					 	$compile($('#enemies'))($scope);
					 	*/
					 	$scope.enemies = data.obj;
					 	$scope.$apply();
				
				});
			}
		}else if(op == 'and'){
			if(name=='' || surname==''){
				$('#err').text('Both fields has to be filled for AND search...');
			}else {
				guestService.search(op,name,surname).success(function(data){
					$scope.enemies = data.obj;
					$scope.$apply();//Opasno treba ispraviti
					$compile($('#enemies'))($scope);
				});
			}
		}
	}
	
	$scope.searchFriends = function(){
		var op = $('#searchSwitchFriend').val();
		var name = $('#searchNameFriend').val();
		var surname = $('#searchSurnameFriend').val();
		
		
		if(op == 'or'){
			if(name != '' && surname != ''){
				$('#errFriend').text("Only one field has to be filled for OR search...");
			}else if(name=='' && surname==''){
				$('#errFriend').text("Just one field has to be filled for OR search...");
			}else {
				if(name=='')name='!x!';
				if(surname=='')surname='!x!';
				guestService.searchFriends(op,name,surname).success(function(data){
					/*$('#enemiesBody').children().remove();
					 var repeat =' <tr ng-repeat="y in enemies"><td>{{y.name}}</td>';
						 repeat+='<td>{{y.surname}}</td>';
					 	 repeat+='<td><button data-baid="{{y.id}}" type="button" class="btn btn-primary btn-xs" ng-click="addFriend(y.id)">add</button></td> </tr>';
					 	$('#enemiesBody').append(repeat);
					 	$compile($('#enemies'))($scope);
					 	*/

					 	$scope.friends = data.obj;
					 	$scope.$apply();//Opasno treba ispraviti
					 
				
				});
			}
		}else if(op == 'and'){
			if(name=='' || surname==''){
				$('#errFriend').text('Both fields has to be filled for AND search...');
			}else {
				guestService.searchFriends(op,name,surname).success(function(data){
					$scope.friends = data.obj;
					$scope.$apply();//Opasno treba ispraviti
					$compile($('#friends'))($scope);
				});
			}
		}
	}
	
	
	$scope.RestaurantsMnu = function(){
		$location.path('/restaurants');
	}
	
	$scope.redirToMyProfile = function(){
		guestService.redirMyProfile();
	}

})