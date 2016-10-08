var myApp = angular.module("myApp", []);
myApp.controller('formCtrl', function($scope) {
    $scope.master = {firstName: "John", lastName: "Doe"};
    $scope.reset = function() {
        $scope.user = angular.copy($scope.master);
    };
    $scope.reset();
});

// create angular app
var validationApp = angular.module('validationApp', []);
// create angular controller
validationApp.controller('mainController', function($scope) {
    $scope.test = {flag:false};
    // function to submit the form after all validation has occurred
    $scope.submitForm = function(isValid) {
        // check to make sure the form is completely valid
        $scope.test.flag = true;
        console.info($scope.test.flag);
        console.info($scope.user.username);
        if (isValid) {
            console.info('our form is amazing');
        }
    };
});


/*
app.controller('formCtrl', function($scope,$http) {
    $scope.login = function() {
        $http({
            url:'/carlt/loginForm',
            method: 'POST',
            data: {name:'angular',password:'333',age:1}
        }).success(function(){
            console.log("success!");
        }).error(function(){
            console.log("error");
        })
    };
});*/
