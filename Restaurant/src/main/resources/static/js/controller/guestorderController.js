var guestorderController = angular.module('restaurantApp.guestorderController',[]);

guestorderController.controller('guestorderController', function($scope, $location, $window, $compile,guestorderService){
	
	var drinkNumber;
	var mealNumber;
	
	guestorderService.getStoloviRezervacije().success(function(data){
		if(data.ok==true){
			var stolovi = data.obj;
			var len = stolovi.length;
			
			for(var i=0; i<len;i++){
				$('#selectStolovi').append('<option value="'+stolovi[i]+'">'+stolovi[i]+'</option>');
			}
			
		}
	
	});
	
	
	
	
	$scope.funcChangeDrink = function(){
		temp = $('#drinks').val();
		drinkNumber = temp;
		$('#appendujDrink').empty();
		//$scope.prom = $('#drinks').val();
		for(i = 0; i<temp; i++){
			$('#appendujDrink').append('<tr><td>Drink '+i+'</td><td><input data-drinkkk=' + i + 'ng-model="drinkk' + i + '" type="text" class="form-control" id="pice" > </td>'
					+'<td><input ng-model="drinksquantity" type="number" id="drinksquantity""></td></tr>');
		}
	}
	
	$scope.funcChangeMeal = function(){
		temp = $('#meals').val();
		mealNumber = temp;
		//$scope.prom = $('#meals').val();
		$('#appendujMeal').empty();
		for(i = 0; i<temp; i++){
			$('#appendujMeal').append('<tr><td>Meal '+i+'</td><td><input ng-model="meall" type="text" class="form-control" id="hrana"> </td>'
					+'<td><input ng-model="mealsquantity" type="number" id="mealsquantity""></td></tr>');
		}
	}
	
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
		
    	guestorderService.addOrder(str).success(function (data){
    		alert("Order added!");
    		$location.path('/homepage');
    	});
    	
	}
	
	
	
})