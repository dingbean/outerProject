var register = angular.module("register", []);

register.controller('registerCtrl', function($scope,$http) {

    $scope.clickForm = function() {
        alert($scope.user.email + "___" + $scope.user.password);
        var data = {userName:$scope.user.email,wxUserName:$scope.user.password};
        $http.post("../../busi/input", data)
            .success(function(){
                console.info("Saved.");
            })
            .error(function(){
                console.error("Failed to save.");
            });
    };
});