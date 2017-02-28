var app = angular.module("restaurantApp.route", ["ngRoute"]);

app.config(function($routeProvider) {
    $routeProvider
    .when("/", {
        templateUrl : "html/login.html"
    })
    .when("/login", {
        templateUrl : "html/login.html"
    }).when("/register", {
        templateUrl : "html/register.html"
    })
    .when("/restaurant", {
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
    }).when("/restmanager", {
        templateUrl : "html/restaurant_manager.html"
    }).when("/restmanager/menus/:menuId", {
        templateUrl : "html/restmanager_menu.html"
    }).when("/restmanager/cards/:menuId", {
        templateUrl : "html/restmanager_card.html"
    }).when("/restmanager/tables",{
    	templateUrl : "html/restmanager_tables.html"
    }).when("/restmanager/staff",{
    	templateUrl : "html/restmanager_staff.html"
    }).when("/restmanager/reports", {
    	templateUrl : "html/restmanager_reports.html"
    });
});