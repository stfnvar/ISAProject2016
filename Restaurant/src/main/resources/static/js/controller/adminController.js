var adminController = angular.module('restaurantApp.adminController', []);

adminController.controller('adminController', function($scope, $location,
		adminService) {

	$scope.currentRestaurantManager = {};
	$scope.currentRestaurantManager.restaurant = {};
	$scope.managers = {};

	adminService.whoIsLogged().success(function(data) {

		if (data.obj != null) {
			$scope.email = data.obj.email;
			adminService.getAdmins().success(function(admins) {
				if (admins.length > 0) {
					$scope.admins = admins;
				} else {
					$scope.message = "No admins";
				}
			});

			adminService.getRestaurants().success(function(data) {
				if (data.length > 0) {
					$scope.restaurants = data;
				} else {
					$scpoe.message = "No restaurants";
				}
			});
		} else
			$location.path('/login');

	});

	$scope.OdjavaFunc = function() {
		adminService.logout().success(function(data) {

			if (data.ok = true)
				$location.path('/login');
		});
	}

	$scope.openCreateModal = function() {
		$('#createModal').modal('show');
	}

	$scope.openModal = function(admin) {
		$scope.currentAdmin = {};
		$('#myModal').modal('show');
		$scope.currentAdmin = angular.copy(admin);
	}

	$scope.addAdmin = function(a) {
		var ta = {
			name : a.name,
			surname : a.surname,
			username : a.email,
			email : a.email,
			password : a.password
		}
		json = JSON.stringify(ta);

		adminService.addAdmin(json).success(function(data) {
			adminService.getAdmins().success(function(admins) {
				if (admins.length > 0) {
					$scope.admins = admins;
				} else {
					$scope.message = "Assistent administrator";
				}
				$("#createModal").modal('hide');
			});
		});
	}

	$scope.modifyAdmin = function(a) {
		var c = angular.copy($scope.currentAdmin);
		var ta = {
			id : c.id,
			name : c.name,
			surname : c.surname,
			username : c.email,
			email : c.email,
			password : c.password
		}
		json = JSON.stringify(ta);

		adminService.modifyAdmin(json).success(function(data) {
			adminService.getAdmins().success(function(admins) {
				if (admins.length > 0) {
					$scope.admins = admins;
				} else {
					$scope.message = "Assistent administrator";
				}
				$("#myModal").modal('hide');
				$scope.currentAdmin = {};
			});
		});
	}

	$scope.removeAdmin = function(a) {
		var ta = {
			id : a.id,
			name : a.name,
			surname : a.surname,
			username : a.email,
			email : a.email
		}
		json = JSON.stringify(ta);

		adminService.removeAdmin(json).success(function(data) {
			adminService.getAdmins().success(function(admins) {
				if (admins.length > 0) {
					$scope.admins = admins;
				} else {
					$scope.message = "Assistent administrator";
				}
				$("#myModal").modal('hide');
			});
		});
	}
	$scope.adminsShow = true;
	$scope.restaurantsShow = false;

	$scope.showAdmins = function() {
		$scope.adminsShow = true;
		$scope.restaurantsShow = false;
	}

	$scope.showRestaurants = function() {
		$scope.restaurantsShow = true;
		$scope.adminsShow = false;
	}

	$scope.removeRestaurant = function(r) {
		var rr = {
			id : r.id,
			name : r.name,
			description : r.description
		}

		json = JSON.stringify(rr);

		adminService.removeRestaurant(json).success(function(data) {
			adminService.getRestaurants().success(function(data) {
				if (data.length > 0) {
					$scope.restaurants = data;
				} else {
					$scpoe.message = "No restaurants";
				}
			});
		});
	}

	$scope.addRestaurant = function(r) {
		var rr = {
			name : r.name,
			description : r.description
		}
		json = JSON.stringify(rr);

		adminService.addRestaurant(json).success(function(data) {
			adminService.getRestaurants().success(function(data) {
				if (data.length > 0) {
					$scope.restaurants = data;
				} else {
					$scpoe.message = "No restaurants";
				}
				$('#createModalRest').modal('hide');
			});
		});
	}

	$scope.openCreateRestaurantModal = function() {
		$("#createModalRest").modal('show');
	}

	$scope.openAddMan = function(restaurant) {
		$scope.currentRestaurantManager.restaurant.id = restaurant.id;
		$("#createModalMan").modal('show');
	}

	$scope.addMan = function(man) {
		var a = {
			email : man.email,
			username : man.email,
			name : man.name,
			password : man.password,
			surname : man.surname,
			restaurant : {
				id : $scope.currentRestaurantManager.restaurant.id
			}
		}
		json = JSON.stringify(a);

		adminService.addManager(a).success()
		{
			$("#createModalMan").modal('hide');
		}
	}
	$scope.openViewManagers = function(restaurant) {
		adminService.getRestaurantManagers(restaurant.id).success(
				function(data) {
					$scope.managers = data;
					$('#viewManagersModal').modal('show');
				})
	}

	$scope.removeManager = function(m) {
		var mm = {
			id : m.id,
			restaurant : {
				id : m.restaurant.id
			},
			name : m.name,
			surname : m.surname,
			email : m.email,
			username : m.username
		}

		json = JSON.stringify(mm);
		adminService.removeRestaurantManager(json).success(
				function(data) {
					adminService.getRestaurantManagers(restaurant.id).success(
							function(data) {
								$scope.managers = data;
							});
				});
	}

})