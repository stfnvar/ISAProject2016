var bartenderController = angular.module('restaurantApp.bartenderController',[]);

guestController.controller('bartenderController', function($scope, $location, $window, $compile, bartenderService){
	
	$('#calendar').fullCalendar({
		dayClick : function(date, jsEvent, view) {
			$('#workModal').modal("show");
			var date = date;
			$scope.date = date;
			var json = JSON.stringify(date);
			bartenderService.getOnDutyDay(json).success(function(data) {
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
	
	var bId;
	
	bartenderService.whoIsLogged().success(function(data){
		
		if(data.obj != null){
			$scope.email=data.obj.email;
			bId = data.obj.id;
			
			$('#email').text(data.obj.email);
			$('#name').text(data.obj.name);
			$('#surname').text(data.obj.surname);
			$('#sifra').text(data.obj.password);
			
			$scope.name=data.obj.name;
			$scope.surname=data.obj.surname;
			
			bartenderService.checkFirstTime(data.obj.id).success(function(data){
				//alert("HOJJJ");

				if(data == ""){
					$('#bartNavigationBar').show();
					$('#bprofil').show();
					$('#firstTimeChange').hide();
				} else {
					$('#bartNavigationBar').hide();
					$('#bprofil').hide();
					$('#firstTimeChange').show();
				}

			}).error(function(data){
				//alert("ERROR");
				$('#bartNavigationBar').show();
			});

		}
		else
			$location.path('/login');

});
	
	
	$scope.bartFTChangePWFun = function(){
		var pw = $scope.bartPassword;
		alert(pw);
		
		bartenderService.ftChangePW(bId, pw).success(function(data){
			alert("uspesno!");
			$location.path('/bartender');
			$window.location.reload();
		});
	}
	
	//odjavljivanje
	$scope.OdjavaFunc = function(){
		bartenderService.logout().success(function(data) {
			
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
		
		
		bartenderService.updateBart(info).success(function(data){
			$('.container').empty();
			$('.container').append('<p>'+data.message+'</p>');
			$location.path('/bartender');
			$window.location.reload();
		});
		
		
	}
	
	
})