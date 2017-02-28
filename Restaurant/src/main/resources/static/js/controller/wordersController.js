var wordersController = angular.module('restaurantApp.wordersController',[]);

wordersController.controller('wordersController', function($scope, $location, $window, $compile, orderService){

	orderService.findAllOrders().success(function(data){
		$scope.allOrders = data;
	});
	
	$scope.billDetails={};
	
	$scope.createBill = function(x){
		orderService.createBill(x).success(function(data){
			alert("uspesno kreiran racun");
			$scope.billDetails.id = data.order.id;
			$scope.billDetails.table = data.order.table;
			$scope.billDetails.table.id = data.order.table.id;
			$scope.billDetails.totalPrice = data.totalPrice;
			orderService.getDrinksByOrder(data.order.id).success(function (data){
				$scope.billDetails.drinks = data;
			});
			orderService.getMealsByOrder(data.order.id).success(function (data){
				$scope.billDetails.meals = data;
			});
			$('#createBillModal').modal('show');
		});
	}
	$scope.orderDetails={};
	
	$scope.details = function(x){
		$scope.orderDetails.id = x.id;
		$scope.orderDetails.table = x.table;
		$scope.orderDetails.table.id = x.table.id;
		orderService.getDrinksByOrder(x.id).success(function (data){
			$scope.orderDetails.drinks = data;
		});
		orderService.getMealsByOrder(x.id).success(function (data){
			$scope.orderDetails.meals = data;
		});
		$('#detailsModal').modal('show');
	}
	
	
	$scope.orderEdit={};
	
	$scope.edit = function(x){
		$scope.orderEdit.id = x.id;
		$scope.orderEdit.table = x.table;
		$scope.orderEdit.table.id = x.table.id;
		orderService.getDrinksByOrder(x.id).success(function (data){
			$scope.orderEdit.drinks = data;
		});
		orderService.getMealsByOrder(x.id).success(function (data){
			$scope.orderEdit.meals = data;
		});
		
		$('#editModal').modal('show');
	}
	
	$scope.editOrder = function(orderEdit){
		var nizM = [];
		var nizD = [];
		var li = [];
		
		var MEALLL = [];
		var DRINKK = [];
		
		for(var i = 0; i < orderEdit.meals.length; i++){
			nizM.push(orderEdit.meals[i].quantity);
			MEALLL.push(orderEdit.meals[i].name);
			li.push(orderEdit.meals[i].id);
		}
		for(var i = 0; i < orderEdit.drinks.length; i++){
			nizD.push(orderEdit.drinks[i].quantity);
			DRINKK.push(orderEdit.drinks[i].name);
			li.push(orderEdit.drinks[i].id);
		}
		//alert(nizM);
		var drinkMeal = {
	    		meals : MEALLL,
	    		drinks : DRINKK,
	    		tableId : orderEdit.table.id,
	    		quantityDrinks : nizD,
	    		quantityMeals : nizM
	    	};
		    	    	
    	var str = JSON.stringify(drinkMeal);
    	//orderService.lili(li).success(function (data){
    	//		alert("li!");
    	//});	
    	//orderService.editOrder(str).success(function (data){
    	//	alert("Order edited!");
    	//});	
    	
    	var ordiDrinks = orderEdit.drinks;
    	var strD = JSON.stringify(ordiDrinks);
    	orderService.editOrderDR(strD).success(function (data){
    		alert("Order drinks edited!");
    	});	
    	
    	var ordiMeals = orderEdit.meals;
    	var strD = JSON.stringify(ordiMeals);
    	orderService.editOrderMEALS(strD).success(function (data){
    		alert("Order meals edited!");
    	});	
    	
	} 
	
	
	
})