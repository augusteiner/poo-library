
(function() {

  'use strict';

  var CTRLR = 'UserCtrlr';
  var ROOT_PATH = '/user';

  var app = angular.module('biblioteca');

  app.config(function($routeProvider) {

    $routeProvider
      .when(ROOT_PATH, {
        templateUrl : 'views' + ROOT_PATH + '/',
        controller : CTRLR
      })
      .when(ROOT_PATH + '/:id/reserva', {
        templateUrl : 'views' + ROOT_PATH + '/reserva/',
        controller : CTRLR
      })
      .when(ROOT_PATH + '/:id/locacao', {
        templateUrl : 'views' + ROOT_PATH + '/locacao/',
        controller : CTRLR
      })
      .when(ROOT_PATH + '/:id/settings', {
        templateUrl : 'views' + ROOT_PATH + '/settings/',
        controller : CTRLR
      })
      /*.otherwise({
        redirectTo: ROOT_PATH
      })*/;
  });

  app.controller(CTRLR, function($scope, $http, $routeParams, $location) {

    //$scope.$parent.menu = 'views' + ROOT_PATH + '/menu/';
    //$scope.$parent.userId = 1;
  });

})();

