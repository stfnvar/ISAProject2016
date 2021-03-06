var restmanager_menuController = angular.module(
		'restaurantApp.restmanager_menuController', []);

restmanager_menuController.controller('restmanager_menuController', function(
		$scope, $location, restmanager_menuService, $routeParams) {

	restmanager_menuService.whoIsLogged().success(function(data) {

		if (data.obj != null) {
			$scope.info = data.obj;
			$scope.itemId = $routeParams.menuId;
		} else
			$location.path('/login');

	});

	restmanager_menuService.getMenuInfo($routeParams.menuId).success(
			function(data) {
				$scope.menuId = data[0];
			});

	restmanager_menuService.getMealsInfo($routeParams.menuId).success(
			function(data) {
				$scope.meals = data;
			});
	$scope.OdjavaFunc = function() {
		restmanager_menuService.logout().success(function(data) {

			if (data.ok = true)
				$location.path('/login');
		});
	}
	$scope.openModal = function(id) {
		$scope.currentMeal = {};
		event.preventDefault();
		$('#myModal').modal('show');
		$scope.currentMeal = angular.copy(id);

	}
	
	$scope.openCreateModal = function(){
		event.preventDefault();
		$('#createModal').modal('show');
	}

	$scope.removeMeal = function($event, meal) {
		var meal = {
			id : meal.id,
			name : meal.name
		}

		var json = JSON.stringify(meal);

		restmanager_menuService.removeMeal(meal).success(
				function(data) {
					restmanager_menuService.getMealsInfo($routeParams.menuId)
							.success(function(data) {
								$scope.meals = data;
								$('#myModal').modal('hide');
							});
				});
	}

	$scope.meal = {};
	
	$scope.addMeal = function($event, meal) {
		var name = $scope.meal.name;
		var description = $scope.meal.description;
		var price = parseFloat($scope.meal.price);
		var menu_id = $routeParams.menuId;
		$scope.meal = {}
		
		var meal = {
			name : name,
			description : description,
			price : price,
			menu : {
				id : menu_id
			}
		}
		var json = JSON.stringify(meal);
		restmanager_menuService.addMeal(json).success(
				function(data) {
					restmanager_menuService.getMealsInfo($routeParams.menuId)
							.success(function(data) {
								$scope.meals = data;
								$('#createModal').modal('hide');
							});
				});
	}

	$scope.modifyMeal = function($event, meal) {
		var price = parseFloat(meal.price);
		var meal = {
			id : meal.id,
			name : meal.name,
			description : meal.description,
			price : price
		}
		var json = JSON.stringify(meal);
		restmanager_menuService.modifyMeal(json).success(
				function(data) {
					restmanager_menuService.getMealsInfo($routeParams.menuId)
							.success(function(data) {
								$scope.meals = data;
								$('#myModal').modal('hide');
							});
				});
	}

})