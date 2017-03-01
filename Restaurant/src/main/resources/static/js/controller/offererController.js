var offererController = angular.module('restaurantApp.offererController', []);


offererController.controller('offererController', function($scope, $location,
		offererService) {
	$scope.firstPassword = {};
	$scope.showChange = false;
	$scope.showProfile = true;
	
	$scope.ofs = {};
	
	$scope.anmt = [];
	
	offererService.whoIsLogged().success(function(data) {

		if (data.message == 'offerer') {
			if (data.obj.firstTime == true) {
				$scope.showChange = true;
				$scope.showProfile = false;
				$scope.info = data.obj;
				$scope.infoEditing = angular.copy(data.obj);
			} else {
				$scope.showChange = false;
				$scope.showProfile = true;
				$scope.info = data.obj;
				$scope.infoEditing = angular.copy(data.obj);
				offererService.getOffers().success(function(ofsi) {
					$scope.ofs = ofsi;
				});
				
				offererService.getAnnoun().success(function (ansi){
					for(i = 0;i < ansi.length;i++){
						var dates = new Date(ansi[i].start);
						var daten = new Date(ansi[i].end);
						var dates = moment(dates).format('MM-DD-YYYY');
						var daten = moment(daten).format('MM-DD-YYYY');
						$scope.anmt.push({id : ansi[i].id, restaurantManager : ansi[i].restaurantManager, start : ansi[i].start, starts : dates, end : ansi[i].end, ends : daten})
					}
					$scope.ans = angular.copy($scope.anmt);
				});
				
				offererService.getOffers().success(function(info){
					$scope.of = info;
				});
			}
		} else
			$location.path('/login');

	});

	$scope.infoEditingName = {};
	$scope.infoEditingSurname = {};

	$scope.OdjavaFunc = function() {
		offererService.logout().success(function(data) {

			if (data.ok = true)
				$location.path('/login');
		});
	}

	$scope.saveName = function() {
		$scope.info.name = $scope.infoEditing.name;
	}
	$scope.saveSurname = function() {
		$scope.info.surname = $scope.infoEditing.surname;
	}

	$scope.changePassword = function() {
		var oldPassword = $scope.oldPassword;
		var newPassword = $scope.newPassword;
		var newPasswordConfirm = $scope.newPasswordConfirm;

	}

	$scope.refreshName = function() {
		$scope.infoEditing.name = $scope.info.name;
		$scope.editName = !$scope.editName;
	}
	$scope.refreshSurname = function() {
		$scope.infoEditing.surname = $scope.info.surname;
		$scope.editSurname = !$scope.editSurname;
	}

	$scope.applyProfileChanges = function() {
		var restmanager = {
			id : $scope.info.id,
			name : $scope.info.name,
			surname : $scope.info.surname
		}
		var jsonObject = JSON.stringify(restmanager);

		offererService.applyChangesToProfile(jsonObject).success(
				function(data) {
					$scope.info = data;
				});
	}

	$scope.changePassword = function() {
		var oldPassword = $scope.pass.op;
		var newPassword = $scope.pass.np;
		var newPasswordConfirm = $scope.pass.cp;

		var data = [ oldPassword, newPassword, newPasswordConfirm ]
		var json = JSON.stringify(data);

		offererService.changePassword(json).success(function(data) {
			if (data.status == "changed") {
				$scope.changeStatus = 1;
				$scope.pass.np = "";
				$scope.pass.cp = "";
				$scope.pass.op = "";
			} else if (data.status == "noAccess") {
				$scope.changeStatus = 2;
			} else if (data.status == "notMatch") {
				$scope.changeStatus = 3;
			} else if (data.status == "minLengthError") {
				$scope.changeStatus = 4;
			}
		});
	}
	
	$scope.firstChange = function() {
		var oldPassword = $scope.firstPassword;
		var newPassword = $scope.firstPassword;
		var newPasswordConfirm = $scope.firstPassword;

		var data = [ oldPassword, newPassword, newPasswordConfirm ]
		var json = JSON.stringify(data);

		offererService.changeFirstPassword(json).success(function(data) {
			$scope.showChange = false;
			$scope.showProfile = true;
		});
	}

	$scope.bid = function(a){
		var ac = false;
		var offer = {
			price : a.price,
			warranty : 0,
			announcement : {
				id : a.id
			},
			offerer : {
				id : $scope.info.id,
			},
			accepted : ac
		}
		json = JSON.stringify(offer);
		
		offererService.addOffer(offer).success(function(data){
			offererService.getOffers().success(function(info){
				$scope.of = info;
			});
		});
	}
})