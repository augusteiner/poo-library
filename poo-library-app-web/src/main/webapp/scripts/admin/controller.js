(function() {
  'use strict';

  var CTRLR = 'AdminCtrlr';
  var ROOT_PATH = '/admin';

  var app = angular.module('biblioteca');

  app.config(function ($routeProvider) {
    $routeProvider.
      when(ROOT_PATH, {
          templateUrl: ROOT_PATH + '/index.html',
          controller: CTRLR
      });
  });

  app.controller(CTRLR, function($scope, $http, $routeParams, $location) {

  });
})();
