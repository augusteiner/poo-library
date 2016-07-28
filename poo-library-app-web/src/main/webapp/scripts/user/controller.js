(function() {

  'use strict';

  var CTRLR = 'UserCtrlr';
  var ROOT_PATH = '/user';

  var app = angular.module('biblioteca');

  app.config(function($routeProvider) {

    $routeProvider.when(ROOT_PATH, {
      templateUrl : ROOT_PATH + '/index.html',
      controller : CTRLR
    });
  });

  app.controller(CTRLR, function($scope, $http, $routeParams, $location) {

  });
})();
