(function() {

  'use strict';

  var CTRLR = 'UserCtrlr';
  var ROOT_PATH = '/user';

  var app = angular.module('biblioteca');

  app.config(function($routeProvider) {

    $routeProvider
      .when(ROOT_PATH, {
        templateUrl : ROOT_PATH + '/',
        controller : CTRLR
      })
      .when(ROOT_PATH + '/:userId/reserva', {
        templateUrl : ROOT_PATH + '/reserva/',
        controller : CTRLR
      })
      .when(ROOT_PATH + '/:userId/locacao', {
        templateUrl : ROOT_PATH + '/locacao/',
        controller : CTRLR
      })
      .when(ROOT_PATH + '/:userId/settings', {
        templateUrl : ROOT_PATH + '/settings/',
        controller : CTRLR
      })
      .otherwise({
        redirectTo: ROOT_PATH
      });
  });

  app.controller(CTRLR, function($scope, $http, $routeParams, $location) {

    $scope.$parent.menu = ROOT_PATH + '/menu.html';
    $scope.$parent.userId = 1;
  });
})();
