var restaurantController = angular.module('restaurantApp.restaurantController', []);


restaurantController.controller('restaurantController', function($scope,$window,$location,$compile, restaurantService) {
	//globalne promenljive za rezervaciju
	var toReserveId='';
	var toReserveName='';
	var reserveTables=[];
	var invitedFriends=[];
	restaurantService.whoIsLogged().success(function(data){
		
		if(data.obj != null){
			
			$scope.email=data.obj.email;
			$scope.idlogged = data.obj.id;
		}else{
			$location.path('/login');
		}
	});
	
	restaurantService.myinv().success(function(data){
		if(data.obj != null){
			$scope.myinv = data.obj;
			
			$compile($('#initationTable'))($scope);
		}
		
		
		
	});
	
	
	
	restaurantService.getAllRestaurants().success(function(data){
		if(data.ok==true ){
			
			$scope.restaurants = data.obj;
			
			
			
		}else
		   $location.path('/error');
	});
	
	
	//prvi korak rezervacije je odabir restorana i pamcenje ID
	$scope.firstStepReserve =function(id, name){
		$scope.toReserveId=id;
		$scope.toReserveName=name;
		
		$('#restaurantsTable').hide();
		$('#secondStep').show();
		
		$('#secondStepBody').append('<tr><td>Date:</td><td><input  type="datetime-local" id="datepicker"></td></tr>');
		$('#secondStepBody').append('<tr><td>Duration:</td><td><input ng-model="hours" type="number" min="1" max="9" value="1" id="duration">hours</td></tr>');
		$('#secondStepBody').append('<tr><td></td><td><input class="btn btn-info" type="button" value="next" ng-click="go3step()" id="step3btn"></td></tr>');
		$('#secondStepBody').append('<tr><td></td><td id="error"></td></tr>')
	
		$compile($('#secondStep'))($scope);
	}
	
	$scope.cancelInv=function(id){
		restaurantService.cancelInv(id).success(function(){
			alert('sucessful canceled invitation');
			
			$('input[data-iid='+id+']').remove();
		});
	}
	$scope.acceptInv=function(id){
		restaurantService.acceptInv(id).success(function(){
			alert('sucessful accepted invitation');
			
			$('input[data-iid='+id+']').remove();
			
			
		});
	}
	$scope.orderNow = function(fromid,start, end,restid){
		
		restaurantService.addToSessionRest(restid).success(function(data){
			alert('added to session rest id '+data.obj);
		});
		
		restaurantService.getTablesForReservation(start, end, fromid).success(function(){
			$location.path("/guestorder");
		});
	}
	
	
	
	$scope.go3step = function(){
		if($('#datepicker').val() == '' || $('#duration').val()==''){
			$('#error').text('Date or hour format error...');
		}else {
			//$scope.hours = $('#duration').val();
			
			var t = $('#datepicker').val();
			var dateTimeParts = t.split('T');
			var  timeParts = dateTimeParts[1].split(':');
			var  dateParts = dateTimeParts[0].split('-');
			console.log(dateTimeParts);
			var d = new Date(dateParts[0], dateParts[1]-1, dateParts[2], timeParts[0], timeParts[1], 0,0);
			var dStart = d.getTime();
			var dEnd = d.getTime()+$scope.hours*3600000;
		
			console.log(dStart);
			console.log(dEnd);
			
		
			
			restaurantService.getAllDesks($scope.toReserveId).success(function(data){
				var stolovi =data.obj;
				var len = stolovi.length;
				var tables = $('#tablesContainer');
				
				for(var i=0; i<len; i++){
					tables.append('<div data-sid='+stolovi[i].id+' ng-click=reserveTable('+stolovi[i].id+') class=square>'+stolovi[i].id+'<div>');
				}
				$('#tablesContainer').append('<tr><td></td><td><input class="btn btn-info" type="button" value="confirm reservation and call friends" ng-click="callFriends()" id="callFriendsBtn"></td></tr>');
				$compile($('#tablesContainer'))($scope);
			});
			
			$scope.callFriends  =  function(){
				
				if(reserveTables.length!=0){
				$('#tablesContainer').hide();
				$('#callFriendsBtn').remove();
				
				$scope.tablesReserved = reserveTables;
				
				$('#thirdStep').hide();
				$('#finalStep').show();
				
				var t = $('#datepicker').val();
				var dateTimeParts = t.split('T');
				var  timeParts = dateTimeParts[1].split(':');
				var  dateParts = dateTimeParts[0].split('-');
				console.log(dateTimeParts);
				var d = new Date(dateParts[0], dateParts[1]-1, dateParts[2], timeParts[0], timeParts[1], 0,0);
				var dStart = d.getTime();
				var dEnd = d.getTime()+$scope.hours*3600000;
				
				
				
				
				
				restaurantService.reserveTables(dStart, dEnd, reserveTables, $scope.toReserveId).success(function(data){
					alert('success reserved tables');
				});
				
				
				restaurantService.getFriends().success(function(data){
					if(data.obj!=null){
						$scope.friends = data.obj;
					}
					
					$compile($("friendsBody"))($scope);
				});
			}else {
				alert('You have to choose table(s)');
			}
				
			
			}
			
			
			
		
				
				$scope.invite = function(to){
					
					var t = $('#datepicker').val();
					var dateTimeParts = t.split('T');
					var  timeParts = dateTimeParts[1].split(':');
					var  dateParts = dateTimeParts[0].split('-');
					console.log(dateTimeParts);
					var d = new Date(dateParts[0], dateParts[1]-1, dateParts[2], timeParts[0], timeParts[1], 0,0);
					var dStart = d.getTime();
					var dEnd = d.getTime()+$scope.hours*3600000;
					
					
					//invitedFriends.push(to);
					
					restaurantService.invite(dStart,dEnd, to, $scope.toReserveId).success(function(){
						
						alert('success invite');
						$('button[data-bid='+to+']').remove();
						
					});
				}
				
				
			
			
			$scope.reserveTable = function(id){
				var arr = $scope.blockedTables;
				
				if(jQuery.inArray(id,arr) == -1 ){
					
					if(jQuery.inArray(id,reserveTables) == -1){
						reserveTables.push(id);
						
						$('div[data-sid='+id+']').css("background", "green");
					}else {
						var index = reserveTables.indexOf(id);
						reserveTables.splice(index,1);
						if(reserveTables.length!=0){
						
						}else{
							alert('Currently reserved 0 tables');
						}
						$('div[data-sid='+id+']').css("background", "tomato");
					}
					
				}else{
				
					alert("Table already reserved...");
				}
			}
			
			restaurantService.getAvailableDesks(dStart, dEnd, $scope.toReserveId).success(function(data){
			
				$scope.blockedTables=data.obj;
				
			});
			
			$scope.dateres = $('#datepicker').val();
			
			$('#secondStep').hide();
			$('#thirdStep').show();
		}
	}
	
	$scope.refresh = function(){
		 $window.location.reload();
	}
	
	
	

	
	
	
	
	

	
})