var myApp = angular.module("myApp", ['ui.router']);

myApp.config(function ($stateProvider, $urlRouterProvider) {

    $urlRouterProvider.when("", "/pageTab");

    $stateProvider
        .state("pageTab", {
            url: "/pageTab",
            templateUrl: "test/pageTab.html"
        })
        .state("pageTab.page1", {
            url:"/page1",
            templateUrl: "test/page1.html"
        })
        .state("pageTab.page2", {
            url:"/page2",
            templateUrl: "test/page2.html"
        })
        .state("pageTab.page3", {
            url:"/page3",
            templateUrl: "test/page3.html"
        });
});

