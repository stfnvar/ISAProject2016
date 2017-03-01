var orderController = angular.module('restaurantApp.orderController',[]);

orderController.controller('orderController', function($scope, $location, $window,loginService, $compile, orderService){
	
	var drinkNumber;
	var mealNumber;
	
	
	
	
	
	$scope.funcChangeDrink = function(){
		temp = $('#drinks').val();
		drinkNumber = temp;
		$('#appendujDrink').empty();
		//$scope.prom = $('#drinks').val();
		for(i = 0; i<temp; i++){
			$('#appendujDrink').append('<tr><td>Drink '+(i+1)+'</td><td><input data-drinkkk=' + i + 'ng-model="drinkk' + i + '" type="text" class="form-control" id="pice" > </td>'
					+'<td><input ng-model="drinksquantity" type="number" id="drinksquantity""></td></tr>');
		}
	}
	
	$scope.funcChangeMeal = function(){
		temp = $('#meals').val();
		mealNumber = temp;
		//$scope.prom = $('#meals').val();
		$('#appendujMeal').empty();
		for(i = 0; i<temp; i++){
			$('#appendujMeal').append('<tr><td>Meal '+ (i+1) +'</td><td><input ng-model="meall" type="text" class="form-control" id="hrana"> </td>'
					+'<td><input ng-model="mealsquantity" type="number" id="mealsquantity""></td></tr>');
		}
	}
	
	
	orderService.findAllTables().success(function(data){
		$("#selectStolovi").append('<option value=""></option>');
		for(var i = 0; i < data.length; i++)
			$("#selectStolovi").append('<option value="'+ data[i].id + '">' + data[i].id + '</option>');
	});
	
	
	$scope.confirmOrder = function(){
		//for(var i = 0; i < drinkNumber; i++){
		
		if($('#selectStolovi').val() == ""){
			alert("Enter table value.");
			return;
		}
		
			var sviInputDrink;	
			sviInputDrink = $('#appendujDrink #pice');
			sviInputMeals = $('#appendujMeal #hrana');
			
			sviQuantityDrink = $("#appendujDrink #drinksquantity");
			sviQuantityMeals = $("#appendujMeal #mealsquantity");
			
			var nizz = [];
			var nizzMeal = [];
			
			var nizQ = [];
			var nizMealQ = [];
			
			for(var j = 0; j < drinkNumber; j++){
				nizz.push(sviInputDrink[j].value);
				nizQ.push(sviQuantityDrink[j].value);
			}
			for(var j = 0; j < mealNumber; j++){
				nizzMeal.push(sviInputMeals[j].value);
				nizMealQ.push(sviQuantityMeals[j].value);
			}
			//alert(nizz);
			//alert(nizQ);
			//alert(nizzMeal);
			//alert(nizMealQ);
		//}
		selectStolovi = $('#selectStolovi').val();
		//alert(selectStolovi);
		
		var drinkMeal = {
    		meals : nizzMeal,
    		drinks : nizz,
    		tableId : selectStolovi,
    		quantityDrinks : nizQ,
    		quantityMeals : nizMealQ
    	};
	    	    	
    	var str = JSON.stringify(drinkMeal);
		
    	orderService.addOrder(str).success(function (data){
    		alert("Order added!");
    		
    		
    		$location.path('/worders');
    	});
    	
	}
	
})
