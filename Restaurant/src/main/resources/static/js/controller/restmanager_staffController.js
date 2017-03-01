var restmanager_staffController = angular.module(
		'restaurantApp.restmanager_staffController', []);

restmanager_staffController.controller('restmanager_staffController', function(
		$scope, $location, restmanager_staffService, restmanager_tablesService) {

	
	$scope.segments = {};
	$('#calendar').fullCalendar({
		dayClick : function(date, jsEvent, view) {
			$('#workModal').modal("show");
			var date = date;
			$scope.date = date;
			var json = JSON.stringify(date);
			restmanager_staffService.getOnDutyDay(json).success(function(data) {
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
		 * select: function(start, end, id, allDay) {
		 * $('#workModal').modal("show"); var date = start; var end = end;
		 * $scope.date = date; var st = JSON.stringify(date); var en =
		 * JSON.stringify(end); var d = st.concat(en);
		 * restmanager_staffService.getOnDuty(d).success(function(data) {
		 * $scope.onDuty = data; }); }
		 */

	})
	
	$scope.workersOptions = {};
	
	$scope.showCalendarFC = false;
	$scope.showEmployeesFC = true;

	$scope.showCal = function() {
		$scope.showCalendarFC = true;
		$scope.showEmployeesFC = false;
	}

	$scope.showEmp = function() {
		$scope.showCalendarFC = false;
		$scope.showEmployeesFC = true;
	}

	$scope.dailyView = function() {
		$('#calendar').fullCalendar('changeView', 'basicDay');
	}

	$scope.weeklyView = function() {
		$('#calendar').fullCalendar('changeView', 'basicWeek');
	}

	$scope.monthlyView = function() {
		$('#calendar').fullCalendar('changeView', 'month');
	}

	$scope.waiters = {};
	$scope.cooks = {};
	$scope.bartenders = {};

	$scope.tempWaiter = {};
	$scope.tempCook = {};
	$scope.tempBartender = {};

	restmanager_staffService.whoIsLogged().success(function(data) {

		if (data.obj != null) {
			$scope.info = data.obj;
			restmanager_staffService.getWaiters().success(function(w) {
				$scope.waiters = w;
			});

			restmanager_staffService.getBartenders().success(function(w) {
				$scope.bartenders = w;
			});

			restmanager_staffService.getCooks().success(function(w) {
				$scope.cooks = w;
			});	
			restmanager_staffService.getWorkers().success(function(data){
				$scope.workersOptions = data;
			});
			
			restmanager_tablesService.getRestaurantSegments().success(function(data){
				$scope.segments = data;
			});
			
		} else
			$location.path('/login');

	});

	$scope.OdjavaFunc = function() {
		restmanager_staffService.logout().success(function(data) {

			if (data.ok = true)
				$location.path('/login');
		});
	}

	$scope.openWaiter = function(obj) {
		$scope.currentWaiter = {};
		event.preventDefault();
		$('#myModalWaiter').modal('show');
		$scope.currentWaiter = angular.copy(obj);
	}

	$scope.openBartender = function(obj) {
		$scope.currentBartender = {};
		event.preventDefault();
		$('#myModalBartender').modal('show');
		$scope.currentBartender = angular.copy(obj);
	}

	$scope.openCook = function(obj) {
		$scope.currentCook = {};
		event.preventDefault();
		$('#myModalCook').modal('show');
		$scope.currentCook = angular.copy(obj);
	}

	$scope.modifyCook = function(c) {
		var cook = {
			name : c.name,
			surname : c.surname,
			email : c.email,
			password : c.password,

		}
	}

	$scope.addCook = function() {
		$scope.tempCook = {};
		event.preventDefault();
		$('#modalCreateCook').modal('show');
	}

	$scope.addWaiter = function() {
		$scope.tempWaiter = {};
		event.preventDefault();
		$('#modalCreateWaiter').modal('show');
	}

	$scope.addBartender = function() {
		$scope.tempBartender = {};
		event.preventDefault();
		$('#modalCreateBartender').modal('show');
	}

	$scope.createW = function(w) {
		var wait = {
			username : w.email,
			name : w.name,
			surname : w.surname,
			shoeSize : w.shoeSize,
			wearSize : w.wearSize,
			birthDate : w.birthDate,
			email : w.email,
			password : w.passwgord,
			restaurant : {
				id : $scope.info.restaurant.id
			},
			type : 0
		}
		var json = JSON.stringify(wait);

		restmanager_staffService.addWaiter(json).success(function(data) {
			restmanager_staffService.getWaiters().success(function(w) {
				$scope.waiters = w;
			});
		});

	}

	$scope.createC = function(c) {
		var uloga = "unknown";
		var cook = {
			username : c.email,
			name : c.name,
			surname : c.surname,
			shoeSize : c.shoeSize,
			wearSize : c.wearSize,
			birthDate : c.birthDate,
			email : c.email,
			password : c.password,
			uloga : uloga,
			restaurant : {
				id : $scope.info.restaurant.id
			},
			type : 0,
			typeC : c.typeC
		}

		var json = JSON.stringify(cook);

		restmanager_staffService.addCook(json).success(function(data) {
			restmanager_staffService.getCooks().success(function(w) {
				$scope.cooks = w;
			});
		});
	}

	$scope.createB = function(b) {
		var bar = {
			username : b.email,
			name : b.name,
			surname : b.surname,
			shoeSize : b.shoeSize,
			wearSize : b.wearSize,
			birthDate : b.birthDate,
			email : b.email,
			password : b.password,
			restaurant : {
				id : $scope.info.restaurant.id
			},
			type : 0
		}
		var json = JSON.stringify(bar);
		restmanager_staffService.addBartender(json).success(function(data) {
			restmanager_staffService.getBartenders().success(function(w) {
				$scope.bartenders = w;
			});
		});
	}
	$scope.selSeg = {};
	$scope.addSchedule = function(currentSchedule, option, o){
		var wid = $('#workerInfoAssign').val();
		var segid = this.segs.id;
		var s = {
			start : currentSchedule.start,
			end : currentSchedule.end,
			shif : currentSchedule.shift,
			worker : {
				id : wid
			},
			segment : {
				id : segid
			}
		}
		json = JSON.stringify(s);
		restmanager_staffService.addSchedule(json).success(function(data){
		});
	}
	
	$scope.removeSchedule = function(event, w){
		var v = {
				id : w.id
		}
		json = JSON.stringify(v);
		restmanager_staffService.removeSchedule(v).success(function(data){
			var d = $scope.date;
			jsond = JSON.stringify(d);
			restmanager_staffService.getOnDutyDay(d).success(function(data) {
				$scope.onDuty = data;
			});
			$scope.date = {};
		});
		
	}
	
	$scope.removeWorker = function(b){
		var bar = {
				id : b.id,
				restaurant :  {
					id : b.restaurant.id
				}
		}
		json = JSON.stringify(bar);
		restmanager_staffService.removeWorker(json).success(function (data){
			restmanager_staffService.getWaiters().success(function(w) {
				$scope.waiters = w;
			});

			restmanager_staffService.getBartenders().success(function(w) {
				$scope.bartenders = w;
			});

			restmanager_staffService.getCooks().success(function(w) {
				$scope.cooks = w;
			});	
			restmanager_staffService.getWorkers().success(function(data){
				$scope.workersOptions = data;
			});
		});
			
		
	}

})