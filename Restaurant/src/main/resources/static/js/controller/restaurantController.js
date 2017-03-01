var restaurantController = angular.module('restaurantApp.restaurantController', []);


restaurantController.controller('restaurantController', function($scope,$window,$location,$compile, restaurantService) {
	//globalne promenljive za rezervaciju
	var toReserveId='';
	var toReserveName='';
	
	restaurantService.whoIsLogged().success(function(data){
		
		if(data.obj != null){
			
			$scope.email=data.obj.email;
		}else{
			$location.path('/login');
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
			
			var intervalObj = {};
			intervalObj.starts = dStart;
			intervalObj.ends = dEnd;
			
			
			console.log(dStart);
			console.log(dEnd);
			
			
			var json = JSON.stringify(intervalObj);
			console.log(json);
			
			restaurantService.getAllDesks($scope.toReserveId).success(function(data){
				var stolovi =data.obj;
				var len = stolovi.length;
				var tables = $('#tablesContainer');
				
				for(var i=0; i<len; i++){
					tables.append('<div data-sid='+stolovi[i].id+' ng-click=reserveTable('+stolovi[i].id+') class=square>'+stolovi[i].id+'<div>');
				}
				
				$compile($('#tablesContainer'))($scope);
			});
			
			
			$scope.reserveTable = function(id){
				alert('reservet this '+id)
			}
			
			restaurantService.getAvailableDesks(dStart, dEnd, $scope.toReserveId).success(function(){
				alert('zabranjeni stolovi');
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