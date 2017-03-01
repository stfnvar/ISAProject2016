var restmanagerController = angular.module('restaurantApp.restmanagerController',[]);

restmanagerController.controller('restmanagerController', function($scope, $location, restmanagerService){

restmanagerService.whoIsLogged().success(function(data){
		
		if(data.obj != null){
				$scope.info=data.obj;
				$scope.infoEditing=angular.copy(data.obj);
				restaurant_id = $scope.info.restaurant.id;
				restmanagerService.getOfferers().success(function (data){
					$scope.offerers = data;
				})
				restmanagerService.getGroceries().success(function (data){
					$scope.groceries = data;
				});
				restmanagerService.getOffers().success(function (data){
					$scope.offers = data;
				});
				
				restmanagerService.getAnnoun().success(function (data){
					$scope.announ = data;
				});
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

$scope.groceries = [];
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


$scope.wantedItems = [];

$scope.pass = {};
	
$scope.OdjavaFunc = function(){
	restmanagerService.logout().success(function(data) {
		if(data.ok = true)
			$location.path('/login');
	});
}

$scope.addToWanted = function(gro){
	if(contains($scope.wantedItems, gro) === false){
		gro.type = 1;
		$scope.wantedItems.push(gro);
		
	}
}


$scope.removeWanted = function(item) { 
	  var index = $scope.wantedItems.indexOf(item);
	  $scope.wantedItems.splice(index, 1);     
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

$scope.openRegisterOffererModal = function(){
	$('#offererModal').modal('show');
}

$scope.registerOfferer = function($event, or){
	var o = {
		companyName : or.companyName,
		name : or.name,
		surname : or.surname,
		email : or.email,
		password : or.password,
		username : or.email
	}
	
	json = JSON.stringify(o);
	
	restmanagerService.addOfferer(o).success(function(data){
		$('#offererModal').modal('hide');
	});
}

$scope.addGrocery = function(g){
	var v = {
		name : g.groNam,
	}
	
	json = JSON.stringify(v);
	
	restmanagerService.addGrocery(json).success(function(data){
		restmanagerService.getGroceries().success(function (data){
			$scope.groceries = data;
		});
	});
}

$scope.seeOffers = function(ae){
	var ann = {
			id : ae.id
	}
	json = JSON.stringify(ann);
	restmanagerService.getOffersForA(json).success(function(data){
		$scope.currentOffersA = data;
	});
	$("#offersModal").modal('show');
	
	
}

$scope.acceptOffer = function (i){
	var acc = i;
	json = JSON.stringify(acc);
	
	restmanagerService.acceptOffer(json).success(function(data){
		$("#offersModal").modal('hide');
	});
}


$scope.seeItems = function(ae){
	var ann = {
		id : ae.id,
	}	
	json = JSON.stringify(ann);
	restmanagerService.getWantedItemsForAnn(json).success(function(data){
		$scope.currentWantedItems = data;
	});
	$("#itemsModal").modal('show');
	
}

function contains(a, obj) {
    for (var i = 0; i < a.length; i++) {
        if (a[i] === obj) {
            return true;
        }
    }
    return false;
}


$scope.addAnnoun = function(g){
	var accepted = false;
	var v = {
		start : g.anStart,
		end : g.anEnd,
		accepted : accepted
	}
	
	json = JSON.stringify(v);
	restmanagerService.addAnnoun(json).success(function(data){
			$scope.announ = data;
			var wi = $scope.wantedItems;
			for(c = 0;c < wi.length;c++){
				var k = {quanitiy : wi[c].quantity, grocery : { id : wi[c].id}, announcement : { id : data.obj.id}};
				jsonwi = JSON.stringify(k);
				restmanagerService.addWantedItems(jsonwi, data.obj.id).success(function(ret){
				});
			}
			$scope.wantedItems = [];
	});
	
	restmanagerService.getAnnoun().success(function (data){
		$scope.announ = data;
	});
}
})