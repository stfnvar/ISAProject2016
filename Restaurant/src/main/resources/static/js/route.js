var app = angular.module("restaurantApp.route", ["ngRoute"]);

app.config(function($routeProvider) {
    $routeProvider
    .when("/", {
        templateUrl : "html/login.html"
    })
    .when("/register", {
        templateUrl : "html/register.html"
    })
    .when("/restaurant", {
        templateUrl : "html/restaurant.html"
    });
});