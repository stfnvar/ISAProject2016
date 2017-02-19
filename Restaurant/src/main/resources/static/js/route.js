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
        templateUrl : "html/restmanager.html"
    });
});