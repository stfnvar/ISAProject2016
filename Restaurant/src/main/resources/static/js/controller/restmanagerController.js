var restmanagerController = angular.module('restaurantApp.restmanagerController',[]);

restmanagerController.controller('restmanagerController', function($scope, $location, restmanagerService){

restmanagerService.whoIsLogged().success(function(data){
		
		if(data.obj != null){
				$scope.info=data.obj;
				$scope.infoEditing=angular.copy(data.obj);
				restaurant_id = $scope.info.restaurant.id;
				
		} else {
			$location.path('/login');
		}
});

restmanagerService.getMenus().success(function(data){
	if(data != null){
		var rangeMenus = [];
		for(var i=0;i<data.length;i++) {
			rangeMenus.push(i);
		}
		$scope.rangeMenus = rangeMenus;
		$scope.menus = data;
	}
});


restmanagerService.getDrinkCards().success(function(data){
if(data != null){
	var rangeDrinkCards = [];
	for(var i=0;i<data.length;i++) {
		rangeDrinkCards.push(i);
	}
	$scope.rangeDrinkCards = rangeDrinkCards;
	$scope.drinkCards = data;
}
});


$scope.pass = {};
	
$scope.OdjavaFunc = function(){
	restmanagerService.logout().success(function(data) {
		if(data.ok = true)
			$location.path('/login');
	});
}

$scope.refreshName = function(){
	$scope.infoEditing.name= $scope.info.name;
	$scope.editName = !$scope.editName;
}
$scope.refreshSurname = function(){
	$scope.infoEditing.surname= $scope.info.surname;
	$scope.editSurname = !$scope.editSurname;
}


$scope.saveName = function(){
	$scope.info.name = $scope.infoEditing.name;
}
$scope.saveSurname = function(){
	$scope.info.surname = $scope.infoEditing.surname;
}

$scope.changePassword = function(){
	var oldPassword = $scope.oldPassword;
	var newPassword = $scope.newPassword;
	var newPasswordConfirm = $scope.newPasswordConfirm;
		
}

$scope.applyProfileChanges = function(){
	var restmanager = {
			id : $scope.info.id,
			name : $scope.info.name,
			surname : $scope.info.surname
	}
	var jsonObject = JSON.stringify(restmanager);
	
	restmanagerService.applyChangesToProfile(jsonObject).success(function(data){
		$scope.info = data;
	});
}


$scope.refreshRestaurantName = function(){
	$scope.infoEditing.restaurant.name= $scope.info.restaurant.name;
	$scope.editRestaurantName = !$scope.editRestaurantName;
}

$scope.refreshRestaurantDescription = function(){
	$scope.infoEditing.restaurant.description= $scope.info.restaurant.description;
	$scope.editRestaurantDescription = !$scope.editRestaurantDescription;
}

$scope.saveRestaurantName = function(){
	$scope.info.restaurant.name = $scope.infoEditing.restaurant.name;
}

$scope.saveRestaurantDescription = function(){
	$scope.info.restaurant.description = $scope.infoEditing.restaurant.description;
}
$scope.changeStatus = 0;

$scope.changePassword = function(){
	var oldPassword = $scope.pass.op;
	var newPassword = $scope.pass.np;
	var newPasswordConfirm = $scope.pass.cp;
	
	var data = [oldPassword, newPassword, newPasswordConfirm]
	var json = JSON.stringify(data);
	
	restmanagerService.changePassword(json).success(function(data){
		if(data.status == "changed"){
			$scope.changeStatus = 1;
			$scope.pass.np = "";
			$scope.pass.cp = "";
			$scope.pass.op = "";
		} else if(data.status == "noAccess"){
			$scope.changeStatus = 2;
		} else if(data.status == "notMatch"){
			$scope.changeStatus = 3;
		} else if(data.status == "minLengthError"){
			$scope.changeStatus = 4;
		}
	});
}
$scope.restmanagerProfile = true;
$scope.restmanagerRestaurant = false;

$scope.showRestmanagerProfile = function(){
	$scope.restmanagerProfile = true;
	$scope.restmanagerRestaurant = false;
}

$scope.showRestmanagerRestaurant = function(){
	$scope.restmanagerRestaurant = true;
	$scope.restmanagerProfile = false;
}


$scope.showDrinkCards = function(){
	$scope.editDrinkCards = true;
	$scope.editMenus = false;
	$scope.editStaff = false;
	$scope.editReports = false;
}

$scope.showMenus = function(){
	$scope.editDrinkCards = false;
	$scope.editMenus = true;
	$scope.editStaff = false;
	$scope.editReports = false;
}

})