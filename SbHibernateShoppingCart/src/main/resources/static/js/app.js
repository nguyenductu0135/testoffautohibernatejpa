// Single Page Application
var app = angular.module('myApp', ['ngRoute'])
app.config(function($routeProvider) {
    $routeProvider
        .when('/', {
            templateUrl: '../pages/home.html'
        })
        .when('/about-us', {
            templateUrl: '../pages/about-us.html'
        })
        
        .when('/product', {
            templateUrl: '../pages/product.html'
        })
        .when('/info', {
            templateUrl: '../pages/info.html'
        })
        .when('/savebook', {
            templateUrl: '../pages/save.html'
        })
    .otherwise({
        redirectTo: '/'
    });
});
//Filter String
app.filter('cut', function() {
    return function(value, wordwise, max, tail) {
        if (!value) return '';

        max = parseInt(max, 10);
        if (!max) return value;
        if (value.length <= max) return value;

        value = value.substr(0, max);
        if (wordwise) {
            var lastspace = value.lastIndexOf(' ');
            if (lastspace !== -1) {
                if (value.charAt(lastspace - 1) === '.' || value.charAt(lastspace - 1) === ',') {
                    lastspace = lastspace - 1;
                }
                value = value.substr(0, lastspace);
            }
        }

        return value + (tail || ' …');
    };
});
//SweetAlert
app.controller('memberLogin', function($scope) {
    $scope.submit = function() {
        Swal.fire({
            position: 'top-end',
            icon: 'success',
            title: 'Your work has been saved',
            showConfirmButton: false,
            timer: 1500
        })
    }
})