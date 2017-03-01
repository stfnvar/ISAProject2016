var wtablesController = angular.module('restaurantApp.wtablesController',[]);

wtablesController.controller('wtablesController', function($scope, $location, $window, $compile, waiterService, orderService){
	
	var wObj;
	
	waiterService.whoIsLogged().success(function(data){
		if(data.obj != null){
			wObj = data.obj;
		}
	
	
	var canvas = new fabric.StaticCanvas('canvas');

	$scope.waiterTables = {};
	
	orderService.findAllTables().success(function(data){
		$scope.tables = data;
		orderService.findara().success(function(data2){
			//alert("yoy");
			$scope.waiterTables = data2;
			
			var count = 0;
			var cnty = 0;
			var cntx = 0;
			for (j = 0; j < data.length; j++) {
				if ((count % 30) == 0) {
					cnty = cnty + 1;
					cntx = 0;
				}
				var y = cnty * 50;
				var x = cntx * 50;
				count = count + 1;
				for (k = 0; k < data2.length; k++) {
					if(data[j].id == data2[k].id ){
						canvas.add(new fabric.Circle({
							radius : 15,
							fill : 'blue',
							top : y,
							left : x
						}));
						cntx = cntx + 1;
						break;
					}
					canvas.add(new fabric.Circle({
						radius : 15,
						fill : '#f55',
						top : y,
						left : x
					}));
					cntx = cntx + 1;
					} 
				}
	});
		});
	
	});
	
})