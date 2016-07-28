(function() {
  'use strict';

  var app = angular.module('biblioteca', ['ngRoute', 'ngResource', 'angular-loading-bar', 'ngAnimate']);

  app.config(['cfpLoadingBarProvider', function(cfpLoadingBarProvider) {
    cfpLoadingBarProvider.includeSpinner = false;
  }]);

  app.config(['$httpProvider', function($httpProvider) {
    // initialize get if not there
    if (!$httpProvider.defaults.headers.get) {
        $httpProvider.defaults.headers.get = {};
    }

    // Answer edited to include suggestions from comments
    // because previous version of code introduced browser-related errors

    // disable IE ajax request caching
    $httpProvider.defaults.headers.get['If-Modified-Since'] = 'Mon, 26 Jul 1997 05:00:00 GMT';
    // extra
    $httpProvider.defaults.headers.get['Cache-Control'] = 'no-cache';
    $httpProvider.defaults.headers.get['Pragma'] = 'no-cache';
  }]);

  app.config(['$routeProvider', function($routeProvider) {
    $routeProvider
      .when('/user', {
        templateUrl: 'user/index.html',
        controller: 'UserCtrlr'
      })
      .when('/admin', {
        templateUrl: 'admin/index.html',
        controller: 'AdminCtrlr'
      });
  }]);
})();
