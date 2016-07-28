(function() {
  'use strict';

  var CTRLR = 'AdminCtrlr';
  var ROOT_PATH = '/admin';

  var app = angular.module('biblioteca');

  app.config(function($routeProvider) {

    $routeProvider.when(ROOT_PATH, {
      templateUrl : 'views' + ROOT_PATH + '/',
      controller : CTRLR
    })/*
    .otherwise({
      redirectTo: ROOT_PATH
    })*/;
  });

  app.controller(CTRLR, function($scope, $http, $routeParams, $location) {

//    $scope.$parent.menu = 'views' + ROOT_PATH + '/menu/';
//    $scope.$parent.userId = 1;
  });
})();
