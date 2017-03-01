var cookController = angular.module('restaurantApp.cookController',[]);

cookController.controller('cookController', function($scope, $location, $window, $compile, cookService){
	
	var cId;
	
	$('#calendar').fullCalendar({
		dayClick : function(date, jsEvent, view) {
			$('#workModal').modal("show");
			var date = date;
			$scope.date = date;
			var json = JSON.stringify(date);
			cookService.getOnDutyDay(json).success(function(data) {
				$scope.onDuty = data;
				if(data.length == null){
					$scope.noDutified = 'No one on duty on this day!';
				} else {
					$scope.noDutified = '';
				}
			});
		},
		selectHelper : true,
		selectable : true,
		default : true,
		/*
		select: function(start, end, id, allDay) {
			$('#workModal').modal("show");
			var date = start;
			var end = end;
			$scope.date = date;
			var st = JSON.stringify(date);
			var en = JSON.stringify(end);
			var d = st.concat(en);
			restmanager_staffService.getOnDuty(d).success(function(data) {
				$scope.onDuty = data;
			});
		}
		*/

	})
	
	cookService.whoIsLogged().success(function(data){
		
		if(data.obj != null){
			$scope.email=data.obj.email;
			cId = data.obj.id;
			
			$('#email').text(data.obj.email);
			$('#name').text(data.obj.name);
			$('#surname').text(data.obj.surname);
			$('#sifra').text(data.obj.password);
			
			$scope.name=data.obj.name;
			$scope.surname=data.obj.surname;
			
			cookService.checkFirstTime(data.obj.id).success(function(data){
				//alert("HOJJJ");

				if(data == ""){
					$('#cookNavigationBar').show();
					$('#cprofil').show();
					$('#firstTimeChange').hide();
				} else {
					$('#cookNavigationBar').hide();
					$('#cprofil').hide();
					$('#firstTimeChange').show();
				}

			}).error(function(data){
				//alert("ERROR");
				$('#cookNavigationBar').show();
			});
		}
		else
			$location.path('/login');

});
	
	$scope.cookFTChangePWFun = function(){
		var pw = $scope.cookPassword;
		alert(pw);
		
		cookService.ftChangePW(cId, pw).success(function(data){
			alert("uspesno!");
			$location.path('/cook');
			$window.location.reload();
		});
	}
	
	//odjavljivanje
	$scope.OdjavaFunc = function(){
		cookService.logout().success(function(data) {
			
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
		
		
		cookService.updateCook(info).success(function(data){
			$('.container').empty();
			$('.container').append('<p>'+data.message+'</p>');
			$location.path('/cook');
			$window.location.reload();
		});
		
		
	}
	
	
})