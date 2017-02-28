var restmanager_tablesController = angular.module(
		'restaurantApp.restmanager_tablesController', []);

restmanager_tablesController.controller('restmanager_tablesController',
		function($scope, $location, restmanager_tablesService) {
			$scope.currentTable = {};
			$scope.tables = {};
			$scope.segments = {};
			$scope.sNames = {};
			$scope.sTypes = {};

			restmanager_tablesService.whoIsLogged().success(
					function(data) {
						if (data.obj != null) {
							$scope.email = data.obj.email;
							$scope.info = data.obj;
							var canvas = new fabric.StaticCanvas('canvas');
							restmanager_tablesService.getTables().success(
									function(tables) {
										$scope.tables = tables;
										var count = 0;
										var cnty = 0;
										var cntx = 0;
										for (j = 0; j < tables.length; j++) {
											if ((count % 30) == 0) {
												cnty = cnty + 1;
												cntx = 0;
											}
											var y = cnty * 50;
											var x = cntx * 50;
											count = count + 1;
											canvas.add(new fabric.Circle({
												radius : 15,
												fill : '#f55',
												top : y,
												left : x
											}));
											cntx = cntx + 1;
										}
									});
						} else
							$location.path('/login');

					});
			restmanager_tablesService.getRestaurantSegments().success(
					function(segments) {
						$scope.segments = segments;
						var names = [];
						var types = [];
						for (i = 0; i < segments.length; i++) {
							names.push(segments[i].name);
							types.push(segments[i].typeOf);
						}
						$scope.sNames = names;
						$scope.sTypes = types;

					});
			$scope.OdjavaFunc = function() {
				restmanager_tablesService.logout().success(function(data) {

					if (data.ok = true)
						$location.path('/login');
				});
			}
			$scope.openModal = function(id) {
				event.preventDefault();
				$('#myModal').modal('show');
				$scope.currentTable = id;
			}

			$scope.openModalSegment = function(id) {
				event.preventDefault();
				$('#myModalSegment').modal('show');
				$scope.currentSegment = id;
			}

			$scope.removeTable = function($event, table) {
				var table = {
					id : table.id,
				}
				var json = JSON.stringify(table);
				restmanager_tablesService.removeTable(json).success(
						function(data) {
							restmanager_tablesService.getTables().success(
									function(data) {
										$scope.tables = data;
										$('#myModal').modal('hide');
									});
						});
			}

			$scope.removeSegment = function($event, segment) {
				var segment = {
					id : segment.id,
				}
				var json = JSON.stringify(segment);
				restmanager_tablesService.removeSegment(json).success(
						function(data) {
							restmanager_tablesService.getRestaurantSegments()
									.success(function(data) {
										$scope.segments = data;
										$('#myModalSegment').modal('hide');
									});
						});
			}

			$scope.table = {};

			$scope.addTable = function($event, table) {
				var seg = $scope.table.segment.id;
				var rest = $scope.info.restaurant.id;
				var t = {
					restaurantSegment : {
						id : seg
					},
					restaurant : {
						id : rest
					}
				}
				var json = JSON.stringify(t);
				restmanager_tablesService.addTable(json).success(
						function(data) {
							restmanager_tablesService.getTables().success(
									function(data) {
										$scope.tables = data;
									});
						});
			}
			$scope.tempSeg = {};
			
			$scope.addSegment = function($event, segment) {
				var name = $scope.tempSeg.name;
				var desc = $scope.tempSeg.typeOf;
				var rest = $scope.info.restaurant.id;
				var t = {
					name : name,
					typeOf : desc,
					restaurant : {
						id : rest
					}
				}
				var json = JSON.stringify(t);
				restmanager_tablesService.addSegment(json).success(
						function(data) {
							restmanager_tablesService.getSegments().success(
									function(data) {
										$scope.segments = data;
									});
						});
			}


			$scope.modifyTable = function($event, table) {
				var id = table.restaurantSegment.id
				var t = {
					id : table.id,
					restaurantSegment : {
						id : id
					}
				}
				var json = JSON.stringify(t);
				restmanager_tablesService.modifyTable(json).success(
						function(data) {
							restmanager_tablesService.getTables().success(
									function(data) {
										$scope.tables = data;
										$('#myModal').modal('hide');
									});
						});
			}
			
			$scope.modifySegment = function($event, segment) {
				var id = segment.id;
				var rest = $scope.info.restaurant.id;
				var seg = {
					name : segment.name,
					id : id,
					typeOf : segment.typeOf,
					restaurant : {
						id : rest
					}
				}
				var json = JSON.stringify(seg);
				restmanager_tablesService.modifySegment(json).success(
						function(data) {
							restmanager_tablesService.getRestaurantSegments().success(
									function(data) {
										$scope.segments = data;
										$('#myModalSegment').modal('hide');
									});
						});
			}

		})