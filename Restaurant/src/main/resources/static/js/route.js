var app = angular.module("restaurantApp.route", [ "ngRoute" ]);

app.config(function($routeProvider) {
	$routeProvider.when("/", {
		templateUrl : "html/login.html"
	}).when("/login", {
		templateUrl : "html/login.html"
	}).when("/register", {
		templateUrl : "html/register.html"
	}).when("/restaurant", {
		templateUrl : "html/restaurant.html"
	}).when("/homepage", {
		templateUrl : "html/homepage.html"
	}).when("/cook", {
		templateUrl : "html/cook.html"
	}).when("/bartender", {
		templateUrl : "html/bartender.html"
	}).when("/waiter", {
		templateUrl : "html/waiter.html"
	}).when("/admin", {
		templateUrl : "html/admin.html"
	}).when("/friends", {
		templateUrl : "html/friends.html"
	}).when("/restaurants", {
		templateUrl : "html/restaurants.html"
	}).when("/restmanager", {
		templateUrl : "html/restmanager.html"
	}).when("/error", {
		templateUrl : "html/error.html"
	}).when("/admin", {
		templateUrl : "html/admin.html"
	}).when("/restmanager/menus/:menuId", {
		templateUrl : "html/restmanager_menu.html"
	}).when("/restmanager/cards/:menuId", {
		templateUrl : "html/restmanager_card.html"
	}).when("/restmanager/tables", {
		templateUrl : "html/restmanager_tables.html"
	}).when("/restmanager/staff", {
		templateUrl : "html/restmanager_staff.html"
	}).when("/restmanager/reports", {
		templateUrl : "html/restmanager_reports.html"
	}).when("/wprofile", {
        templateUrl : "html/waiter.html"
    }).when("/worders", {
        templateUrl : "html/worders.html"
    }).when("/newOrder",{
    	templateUrl : "html/newOrder.html"
    }).when("/bprofile",{
    	templateUrl : "html/bartender.html"
    }).when("/borders",{
    	templateUrl : "html/borders.html"
    }).when("/cprofile",{
    	templateUrl : "html/cook.html"
    }).when("/corders",{
    	templateUrl : "html/corders.html"
    }).when("/bschedule",{
    	templateUrl : "html/bschedule.html"
    }).when("/wschedule",{
    	templateUrl : "html/wschedule.html"
    }).when("/guestorder",{
    	templateUrl : "html/guestOrder.html"
    });
});