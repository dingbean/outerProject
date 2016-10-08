/**
 * Created by HP on 2016/10/2.
 */
var myApp = angular.module("myApp", []);
/*myApp.directive("hello",function(){
    return {
        restrict:'E',
        template:'<div>hello everyone</div>',
        replace:true
    }
});*/
/*
myApp.directive("hello",function(){
    return {
        restrict:'E',
        templateUrl:'tpls/tpls1.html',
        replace:true
    }
});*/
/*注射器加载完所有模块时，此方法执行一次*/
/*
myApp.run(function($templateCache){
    $templateCache.put('tpls/tpls2.html','<div>hello $templateCache everyone</div>');
});
myApp.directive("hello",function($templateCache){
    return {
        restrict:'E',
        template:$templateCache.get('tpls/tpls2.html'),
        replace:true
    }
});*/
/*嵌套*/
myApp.directive("hello",function(){
    return {
        restrict:'E',
        scope:{},
        templateUrl:'tpls/tpls1.html',
        transclude:true
    }
});
myApp.controller("myCtrl",["$scope",function($scope){
    $scope.loadData = function(){
        alert("数据加载");
    };
}]);
myApp.controller("myCtrl2",["$scope",function($scope){
    $scope.loadData2 = function(){
        alert("数据加载2");
    };
}]);
myApp.directive("loader",function(){
    return {
        restrict:'E',
        link:function(scope,element,attr){
            element.bind("mouseover",function(){
                //scope.loadData();
                //scope.$apply("loadData()");
                scope.$apply(attr.howtoload);
            });
        }
    }
});
myApp.controller("myCtrl3",["$scope",function($scope){
    $scope.ctrlFlavor = "测试数据";
    $scope.sayHello = function(name){
        alert("Hello " + name);
    }
}]);
myApp.directive("drink",function(){
    return {
        restrict:'E',
        template:'<div>{{flavor}}</div>',
        scope:{
            flavor:'@'
        }
        /*link:function(scope,element,attr){
            scope.flavor = attr.flavor;
        }*/
    }
});
myApp.directive("drinkin",function(){
    return {
        restrict:'AE',
        template:'<input type="text" ng-model="flavor"/>',
        scope:{
            flavor:'='
        }
        /*link:function(scope,element,attr){
         scope.flavor = attr.flavor;
         }*/
    }
});
myApp.directive("greeting",function(){
    return {
        restrict:'AE',
        template:'<input type="text" ng-model="userName"/><button class="btn btn-primary" ng-click="greet({name:userName})">greeting</button> ',
        scope:{
            greet:'&'
        }
        /*link:function(scope,element,attr){
         scope.flavor = attr.flavor;
         }*/
    }
});
